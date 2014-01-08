package windlass

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import windlass.http.{ResponseProcessor, RequestProcessor}

class WebApp(val interface: String = "localhost", val port: Int = 9000, val beforeAll: Seq[RequestProcessor] = Seq.empty, val afterAll: Seq[ResponseProcessor] = Seq.empty) {
  implicit val system = WebApp.actorSystem
  val handler = system.actorOf(WindlassService.props(beforeAll, afterAll))
  
  def start = IO(Http) ! Http.Bind(handler, interface, port)
}

object WebApp {
  lazy val actorSystem = ActorSystem()
}