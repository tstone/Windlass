package simple

import windlass._
import windlass.processors._
import windlass.http.Response
import windlass.WebApp._

object Main {
  def main(args: Array[String]) = {
    new windlass.WebApp(beforeAll = Seq(SimpleRouter), 
        afterAll = Seq(XPoweredBy("Windlass"), ((resp:Response) => { resp.body(resp.body.toUpperCase) }))).start
  }
}
