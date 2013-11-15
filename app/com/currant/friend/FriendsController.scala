package com.currant.friend

import com.currant.base.CurrantController
import play.api.mvc._

object FriendsController extends CurrantController {

  def find() = play.mvc.Results.TODO
  def find(currantProfileId : Float, friendType : String) = play.mvc.Results.TODO

  def update( action: String ) = play.mvc.Results.TODO
}
