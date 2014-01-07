package windlass

package object http {
  type RequestProcessor  = (Request)  => Either[Request, Response]
  type ResponseProcessor = (Response) => Response
}
