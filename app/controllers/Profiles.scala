package controllers

import play.api.mvc._

/**
 * Created by Neosavvy
 *
 * User: adamparrish
 * Date: 11/9/13
 * Time: 12:40 PM
 */
object Profiles  extends Controller {

  def findByFacebookId( facebookId : String ) = play.mvc.Results.TODO

  def updatePushIdentifier() = play.mvc.Results.TODO

  def update( id: Float) = play.mvc.Results.TODO

  def find(id: Float) = play.mvc.Results.TODO

  def findExtended(id: Float) = play.mvc.Results.TODO

  def findClubs(currantUserId:Float) = play.mvc.Results.TODO

  def exploreClubs(currantUserId:Float) = play.mvc.Results.TODO

  def joinClub(currantUserId:Float) = play.mvc.Results.TODO

  def updateClubs(currantUserId:Float) = play.mvc.Results.TODO

  def leaveClub(currantUserId:Float, clubId:Float) = play.mvc.Results.TODO

  def findFriends(currantUserId: Float, friendStatus: String) = play.mvc.Results.TODO

  def findAllGames(id: Float, location: String) = play.mvc.Results.TODO

  def updateFriends(currantUserId: Float) = play.mvc.Results.TODO

  def updateGames(id: Float) = play.mvc.Results.TODO
}
