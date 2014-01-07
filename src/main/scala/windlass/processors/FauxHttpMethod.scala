package windlass.processors

import windlass.http._

object FauxHttpMethod extends RequestProcessor {
  def apply(req: Request) = Left(
    req.formParam("_method")
      .map(req.method(_))
      .getOrElse(req)
  )
}