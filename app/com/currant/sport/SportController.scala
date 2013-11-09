package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc.Action
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.currant.model.SportCreateRequest
import play.api.http.MimeTypes


object SportController extends CurrantController{


  implicit val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportCreateWrites = Json.writes[SportCreateRequest]

  def create = Action { request =>
    request.body.asJson.map(json => {

      val t = json.asOpt[SportCreateRequest]

      val result = t.map(x=>Json.toJson(x))

      val x = result.map(x=>Ok(x))
      x.getOrElse(BadRequest(s"Not expected form: ${request.body}"))
    }).getOrElse(BadRequest(s"Invalid JSON: ${request.body}"))

  }


}