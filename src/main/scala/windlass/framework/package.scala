package windlass

import windlass.http._

package object framework {
  type ControllerAction = (Request) => Response
}
