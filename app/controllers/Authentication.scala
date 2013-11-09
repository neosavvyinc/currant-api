package controllers

import play.api.mvc._

/**
 * Created by Neosavvy
 *
 * User: adamparrish
 * Date: 11/9/13
 * Time: 12:41 PM
 */
object Authentication  extends Controller {

  def login = Action { request =>
    Ok("It works!")
  }

}
