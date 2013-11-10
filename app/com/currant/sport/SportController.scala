package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc.Action
import play.api.libs.json._
import com.currant.model.CurrantException
import scala.concurrent._
import scala.concurrent.Future._
import scala.util.Try

object SportController extends CurrantController{


  implicit val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportCreateWrites = Json.writes[SportCreateRequest]

  val sportWrites = Json.writes[Sport]

  def create = Action.async(parse.json) { request =>
    val t = Try(request.body.as[SportCreateRequest])
    val response = t.map(doSomething(_)).recover{case t: Throwable => failed(t)}
    response.map(toJson(_)).get
  }

  def get = Action.async(parse.json) { request =>
    val success = successful(Sport(1, "name", "description", true, None, None, None, None))
    toJson(success)(sportWrites)

  }

  private def doSomething(scr : SportCreateRequest) : Future[SportCreateRequest] = {
    log.info(s"scr : $scr")
    scr.name match {
      case "badWithCurrantException" => failed(CurrantException(100, "message"))
      case "badWithNoException" => failed(new Exception("untyped exception"))
      case _ => successful(scr)
    }
  }

}