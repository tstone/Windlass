package simple

import windlass._
import windlass.processors._

new WebApp()
  .beforeAll(FauxHttpMethod)
  .afterAll(XPoweredBy("Windlass"))