package windlass.http

// This is extremely simply for now
// but I figured complexity can be built-up
// as needed

class Response(_statusCode: Int, _body: String = "", _headers: Map[String, String] = Map[String, String]()) {
  def copy(statusCode: Int = _statusCode, body: String = _body, headers: Map[String, String] = _headers) =
    new Response(statusCode, body, headers)

  val statusCode: Int = _statusCode
  def statusCode(s: Int): Response = this.copy(statusCode = s)

  val body: String = _body
  def body(b: String): Response = this.copy(body = b)

  val headers: Map[String, String] = _headers
  def headers(hs: Map[String, String]) = this.copy(headers = hs)

  def header(header: String, value: String) = this.copy(headers = this.headers ++ Map(header -> value))
}