import play.api.GlobalSettings
import play.api.mvc.{Results, SimpleResult, RequestHeader, Result}
import scala.concurrent.Future
import com.currant.model.CurrantException
import play.api.libs.json.{JsObject, Json}
import play.api.i18n.Messages
import scala.concurrent.Future._


object Global extends GlobalSettings with Results{

  private val errorCode = "errorCode"
  private val errorMessage = "errorMessage"
  private val displayErrorMessage = "displayErrorMessage"

  override def onError(request: RequestHeader, ex: Throwable): Result = {
    convertThrowable(ex)
  }

  def convertThrowable(t: Throwable) = {
    t match {
      case x: CurrantException => error(x)
      case x: Throwable => BadRequest(jsonException(-1, x.getMessage))
    }
  }

  def error(exception: CurrantException) = {
     BadRequest(jsonException(exception.code, exception.message))
   }

  def jsonException(code: Int = -1, error: String = "") = {
      Json.obj(
        errorCode -> code,
        errorMessage -> error,
        displayErrorMessage -> Messages(code.toString)
      )
    }

}