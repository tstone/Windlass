package windlass.http

import spray.http.Uri

// TODO: Form parameters
// Also: How much does this type lean on the spray types (like Uri)?

class Request(_method: String, _url: Uri, _headers: Map[String, String], _body: String) {
  def header(header: String, value: String) = this.copy(headers = _headers ++ Map(header -> value))
  def copy(method: String = _method, url: Uri = _url, headers: Map[String, String] = _headers, body: String = _body) =
    new Request(method, url, headers, body)

  val url: Uri = _url
  def url(u: Uri): Request = this.copy(url = u)

  val method: String = _method
  def method(m: String): Request = this.copy(method = m)

  val headers: Map[String, String] = _headers
  def headers(hs: Map[String, String]): Request = this.copy(headers = hs)

  val body: String = _body
  def body(b: String) = this.copy(body = b)

  lazy val queryStrings: Map[String, String] = url.query.toMap
  lazy val formParams: Map[String, String] = ???
  lazy val params = queryStrings ++ formParams

  def queryString(key: String): Option[String] = url.query.get(key)
  def formParam(key: String): Option[String] = ???

//  def param(key: String): Option[String] = queryString(key).orElse(formParam(key))
  def param(key: String) = queryString(key)
}