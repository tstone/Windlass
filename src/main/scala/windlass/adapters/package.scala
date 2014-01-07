package windlass

import spray.http._
import spray.http.HttpHeaders.RawHeader
import windlass.http.{Request, Response}

package object adapters {
  implicit class HttpRequestOps(req: HttpRequest) {
    def toRequest = new Request(
      method = req.method.toString,
      uri = req.uri,
      body = req.entity.toString,
      headers = req.headers.foldLeft(Map[String, String]()) {
        case (acc: Map[String, String], header: HttpHeader) => acc ++ Map(header.name -> header.value)
      }
    )
  }

  implicit class ResponseOps(resp: Response) {
    def toHttpResponse = HttpResponse(
      status = StatusCodes.registerCustom(resp.statusCode, "windlass", "", resp.statusCode < 400, true),
      entity = HttpEntity(resp.body),
      headers = resp.headers.foldLeft(List[HttpHeader]()) {
        case (acc: List[HttpHeader], (key: String, value: String)) =>
          acc ::: List(RawHeader(key, value))
      }
    )
  }
}
