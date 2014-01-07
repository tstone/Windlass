package windlass

import akka.actor._
import spray.can.Http
import spray.can.HttpManager
import spray.http._
import windlass.adapters._
import windlass.http._


class WindlassService(beforeAll: Seq[RequestProcessor], afterAll: Seq[ResponseProcessor]) extends Actor with ActorLogging {
  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)
    case req: HttpRequest  => sender ! pipeline(req.toRequest).toHttpResponse
  }

  private def pipeline(req: Request) = responsePipeline(requestPipeline(req))

  private def requestPipeline(req: Request, processors: Seq[RequestProcessor] = beforeAll): Response = processors match {
    case Nil                    => new Response(422)
    case proc :: remainingProcs => proc(req) match {
      case Left(newReq)         => requestPipeline(newReq, remainingProcs)
      case Right(resp)          => resp
    }
  }

  private def responsePipeline(resp: Response, processors: Seq[ResponseProcessor] = afterAll): Response = processors match {
    case Nil                    => resp
    case proc :: remainingProcs => responsePipeline(proc(resp), remainingProcs)
  }
}

object WindlassService {
  def props(beforeAll: Seq[RequestProcessor], afterAll: Seq[ResponseProcessor]) =
    Props(classOf[WindlassService], beforeAll, afterAll)
}