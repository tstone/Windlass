package simple

import simple.SimpleRouter
import windlass._
import windlass.processors._

object Main {
  def main(args: Array[String]) = {
    new WebApp()
      .beforeAll(SimpleRouter)
      .afterAll(XPoweredBy("Windlass"))
  }
}
