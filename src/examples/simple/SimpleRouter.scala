package simple

import windlass.http._
import windlass.processors.Router

object SimpleRouter extends Router {
  def route(req: Request) = {
    if (req.url.endsWith("/test")) SimpleController.test("yello")
    else SimpleController.notFound
  }
}