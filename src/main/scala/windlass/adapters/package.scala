package windlass

import spray.http._
import spray.http.HttpHeaders.RawHeader
import windlass.http.{Request, Response}

package object adapters {
  implicit class HttpRequestOps(req: HttpRequest) {
    def toRequest = new Request(
      _method = req.method.toString,
      _url = req.uri,
      _body = req.entity.toString,
      _headers = req.headers.foldLeft(Map[String, String]()) {
        case (acc: Map[String, String], header: HttpHeader) => acc ++ Map(header.name -> header.value)
      }
    )
  }

  implicit class ResponseOps(resp: Response) {
    def toHttpResponse = HttpResponse(
      status = statusCodeMap(resp.statusCode),
      entity = HttpEntity(resp.body),
      headers = resp.headers.foldLeft(List[HttpHeader]()) {
        case (acc: List[HttpHeader], (key: String, value: String)) =>
          acc ::: List(RawHeader(key, value))
      }
    )

    private def statusCodeMap(code: Int) = code match {
      // TODO: Finish all of these:
      case 200 => StatusCodes.OK
      case 404 => StatusCodes.NotFound
      case 422 => StatusCodes.UnprocessableEntity
      case c: Int => StatusCodes.registerCustom(c, "windlass", "", c < 400, true)
    }
  }
}
