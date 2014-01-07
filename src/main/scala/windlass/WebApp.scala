package windlass

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import windlass.http.{ResponseProcessor, RequestProcessor}
import windlass.WebApp.WebAppConfig

class WebApp(val interface: String = "localhost", val port: Int = 9000, val config: WebAppConfig = WebAppConfig()) {
  implicit val system = config.actorSystem
  val handler = system.actorOf(WindlassService.props(config.beforeAll, config.afterAll))
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