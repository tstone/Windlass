package windlass.http

import spray.http.Uri

// TODO: Form parameters

// How much does this type lean on the spray types (like Uri)?

case class Request(method: String, url: Uri, headers: Map[String, String], body: String) {
  lazy val queryStrings: Map[String, String] = url.query.toMap
  lazy val formParams: Map[String, String] = ???
  lazy val params = queryStrings ++ formParams

  def queryString(key: String): Option[String] = url.query.get(key)
  def formParam(key: String): Option[String] = ???

//  def param(key: String): Option[String] = queryString(key).orElse(formParam(key))
  def param(key: String) = queryString(key)

  // Immutable copy methods
  def method(m: String) = this.copy(method = m)
  def url(u: String) = this.copy(url = u)
  def headers(hs: Map[String, String]) = this.copy(headers = hs)
  def header(header: String, value: String) = this.copy(headers = this.headers ++ Map(header -> value))
  def body(b: String) = this.copy(body = b)
}