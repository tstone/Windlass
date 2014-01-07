package windlass.framework

import windlass.http._

trait Controller {
  def Action(act: => Response): ControllerAction = (_: Request) => act
  def Action(act: (Request) => Response): ControllerAction = (req: Request) => act(req)
  def Response(statusCode: Int, body: String = "") = new Response(statusCode, body)
}