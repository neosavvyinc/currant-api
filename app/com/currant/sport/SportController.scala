package com.currant.sport

import com.currant.base.CurrantController
import play.api.mvc.Action
import play.api.libs.json._
import com.currant.model.Auditable
import scala.concurrent._
import play.api.Logger

//for now
import ExecutionContext.Implicits.global

trait SportController  { this : CurrantController =>

  val sportCreateReads = Json.reads[SportCreateRequest]
    implicit val sportWrites = Json.writes[Sport]
    val sportReads = Json.reads[Sport]

    def create = Action.async(parse.json) { request =>
      Logger.debug(s"creating sport ${request.body}")
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

object SportController extends CurrantController with SportController