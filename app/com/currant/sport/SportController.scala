package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc._
import play.api.libs.json._
import com.currant.model.Auditable
import scala.concurrent._
//for now
import ExecutionContext.Implicits.global

object SportController extends CurrantController{

  val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportWrites = Json.writes[Sport]
  val sportReads = Json.reads[Sport]

  def create = Action(parse.json) { request =>
    val req = request.body.as[SportCreateRequest](sportCreateReads)
    Async {
      toJson(Sport.create(Auditable(req, "user")))
    }
  }

  def get = Action { request =>
    Async {
      toJson(Sport.getAll)
    }
  }

  def update = Action(parse.json) { request =>
    Async {
      toJson(Sport.update(request.body.as[Sport](sportReads)))
    }
  }
}