package com.currant.user

import com.currant.base.CurrantController
import play.api.mvc._

/**
 * Created by Neosavvy
 *
 * User: adamparrish
 * Date: 11/9/13
 * Time: 11:53 AM
 */
object UsersController extends CurrantController {

  def register( id : Long ) = Action { request =>
    Ok("It works!")
  }

}
