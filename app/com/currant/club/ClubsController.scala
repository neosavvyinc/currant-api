package com.currant.club

import play.api.mvc._

object ClubsController extends Controller {
  def create() = play.mvc.Results.TODO

  def findMembers( clubId: Float ) = play.mvc.Results.TODO

  def findClubInfo( clubId: Float ) = play.mvc.Results.TODO

  def findMatches( clubId: Float, matchType: String) = play.mvc.Results.TODO
}
