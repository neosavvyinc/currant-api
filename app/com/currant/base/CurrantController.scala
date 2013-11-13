package com.currant.base

import play.api.mvc.{SimpleResult, Controller}
import play.api.libs.json.{Json, Writes}
import play.api.i18n.Messages
import scala.concurrent._
import ExecutionContext.Implicits.global //for now

trait CurrantController extends Controller with Logging{

  private val errorCode = "errorCode"
  private val errorMessage = "errorMessage"
  private val displayErrorMessage = "displayErrorMessage"
  private val payload = "payload"

  def toJson[T](result: Future[T])(implicit writes: Writes[T]): Future[SimpleResult] = {
    result.map(ok(_))
  }

  def ok[T](result: T)(implicit writes: Writes[T]) = {
    val jsPayload = Json.toJson(result)
    val jsResult = jsonException() + (payload -> jsPayload)
    Ok(jsResult)
  }


  def jsonException(code: Int = -1, error: String = "") = {
    Json.obj(
      errorCode -> code,
      errorMessage -> errorMessage,
      displayErrorMessage -> Messages(code.toString)
    )
  }
}