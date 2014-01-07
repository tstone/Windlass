package windlass

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import windlass.http.{ResponseProcessor, RequestProcessor}
import windlass.WebApp.WebAppConfig

class WebApp(val interface: String = "127.0.0.1", val port: Int = 9000, val config: WebAppConfig = WebAppConfig()) extends App {
  implicit val system = config.actorSystem
  val handler = system.actorOf(Props[WindlassService], name = "windlass-handler")
  IO(Http) ! Http.Bind(handler, interface, port)

  def beforeAll(procs: Seq[RequestProcessor]): WebApp = new WebApp(interface, port, config.copy(beforeAll = procs))
  def afterAll(procs: Seq[ResponseProcessor]): WebApp = new WebApp(interface, port, config.copy(afterAll = procs))
}

object WebApp {
  case class WebAppConfig(
    beforeAll: Seq[RequestProcessor] = Seq.empty,
    afterAll: Seq[ResponseProcessor] = Seq.empty
  ) {
    val actorSystem = ActorSystem()
  }
}