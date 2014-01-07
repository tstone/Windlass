package windlass.processors

import windlass.http._

class XPoweredBy(pb: String = "Windlass") extends ResponseProcessor {
  def apply(resp: Response) =
    resp.header("X-Powered-By", pb)
}

object XPoweredBy {
  def apply(pb: String) = new XPoweredBy(pb)
}