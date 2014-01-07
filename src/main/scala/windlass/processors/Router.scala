package windlass.processors

import windlass.http._
import windlass.framework.ControllerAction

/**
 * Router -- Used when the router will *always* handle a route
 */
trait Router extends RequestProcessor {
  def route(req: Request): ControllerAction
  def apply(req: Request): Either[Request, Response] =
    Right(route(req)(req))
}