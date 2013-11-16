import play.api.{Application, GlobalSettings}
import play.api.mvc.{Results, SimpleResult, RequestHeader}
import scala.concurrent.Future
import com.currant.model.CurrantException
import play.api.libs.json.Json
import play.api.i18n.Messages
import scala.concurrent.Future._


object Global extends GlobalSettings with Results{

  import play.api.http.Status._

  private val errorCode = "errorCode"
  private val errorMessage = "errorMessage"
  private val displayErrorMessage = "displayErrorMessage"

  override def onError(request: RequestHeader, ex: Throwable): Future[SimpleResult] = {
    successful {
      convertThrowable(ex)
    }
  }


  override def onBadRequest(request: RequestHeader, error: String): Future[SimpleResult] =  {
    successful {
      BadRequest(jsonException(BAD_REQUEST, error))
    }
  }


  override def onHandlerNotFound(request: RequestHeader): Future[SimpleResult] = {
    successful {
      NotFound(jsonException(NOT_FOUND, "not found"))
    }
  }

  def convertThrowable(t: Throwable) = {
    t match {
      case x: CurrantException => error(x)
      case x: Throwable => BadRequest(jsonException(-1, x.getMessage))
    }
  }


  override def onStop(app: Application): Unit = {
    Thread.sleep(1000)
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