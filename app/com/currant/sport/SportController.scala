package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc.Action
import play.api.libs.json._
import com.currant.model.Auditable
import scala.concurrent._
//for now
import ExecutionContext.Implicits.global

object SportController extends CurrantController{

  val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportWrites = Json.writes[Sport]
  val sportReads = Json.reads[Sport]

  def create = Action.async(parse.json) { request =>
    val req = request.body.as[SportCreateRequest](sportCreateReads)
    toJson(Sport.create(Auditable(req, "user")))
  }

  def get = Action.async { request =>
    toJson(Sport.getAll)
  }

  def update = Action.async(parse.json) { request =>
    toJson(Sport.update(request.body.as[Sport](sportReads)))
  }
}