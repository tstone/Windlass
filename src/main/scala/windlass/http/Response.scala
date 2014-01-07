package windlass.http

// This is extremely simply for now
// but I figured complexity can be built-up
// as needed

case class Response(statusCode: Int, body: String = "", headers: Map[String, String] = Map[String, String]()) {
  def statusCode(sc: Int) = this.copy(statusCode = sc)
  def body(b: String) = this.copy(body = b)
  def headers(hs: Map[String, String]) = this.copy(headers = hs)
  def header(header: String, value: String) = this.copy(headers = this.headers ++ Map(header -> value))
}