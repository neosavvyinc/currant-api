package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc.Action
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.currant.model.SportCreateRequest


object SportController extends CurrantController{


  implicit val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportCreateWrites = Json.writes[SportCreateRequest]

  def create = Action { request =>
    request.body.asJson.map(json => {
     /* val t = Try(json.as[SportCreateRequest])
      val mapped = t.map(x=> OK(Json.obj("status"->"OK")))
      val result = mapped.recover(case t : Throwable => )*/
      val t = json.asOpt[SportCreateRequest]
      val result = t.map(x=>Json.toJson(x))
      val x = result.map(x=>Ok(Json.stringify(x)))
      x.getOrElse(BadRequest(s"Not expected form: ${request.body}"))
      Ok(Json.obj("status"->"ok"))
    }).getOrElse(BadRequest(s"Invalid JSON: ${request.body}"))

  }


}