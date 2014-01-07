package windlass.processors

import windlass.http._
import windlass.framework.ControllerAction

/**
 * PartialRouter -- Used when the router should have "fall through" behavior
 */
trait PartialRouter extends RequestProcessor {
  def route: PartialFunction[Request, ControllerAction]
  def apply(req: Request): Either[Request, Response] =
    if (route.isDefinedAt(req)) Right(route(req)(req))
    else Left(req)
}