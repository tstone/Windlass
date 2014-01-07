package simple

import windlass.framework.Controller

object SimpleController extends Controller {
  def test(s: String) = Action {
    Response(200, s"Hello, $s")
  }

  def notFound = Action {
    Response(404, "Not Found")
  }
}