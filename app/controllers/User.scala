package controllers

import play.api.mvc._

/**
 * Created by Neosavvy
 *
 * User: adamparrish
 * Date: 11/9/13
 * Time: 11:53 AM
 */
object User extends Controller {

  def register( id : Long ) = Action { request =>
    Ok("It works!")
  }

}
