package com.currant.base

import play.api.mvc.Controller
import play.api.libs.json.{Json, Writes}
import com.currant.model.Response


trait CurrantController extends Controller{

//TODO: how to serialize generic classes in play
  //implicit val responseWriter = Writes[Response]

  /*def okJson[T](result : T) = {
    implicit val writer = Json.writes[Response[T]]
    Ok(Json.toJson(Response(result)))

  }*/





  def jsonRResponse[T](result : Option[T]) = {

  }

}