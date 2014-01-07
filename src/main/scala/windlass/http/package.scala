package windlass

package object http {
  type RequestProcessor  = (Request)  => Either[Request, Response]
  type ResponseProcessor = (Response) => Response

  implicit def reqP2Seq(rp: RequestProcessor) = Seq(rp)
  implicit def respP2Seq(rp: ResponseProcessor) = Seq(rp)
}
