package com.currant.club

import com.currant.base.CurrantController
import play.api.mvc._

object ClubsController extends CurrantController {
  def create() = play.mvc.Results.TODO

  def findMembers( clubId: Float ) = play.mvc.Results.TODO

  def findClubInfo( clubId: Float ) = play.mvc.Results.TODO

  def findMatches( clubId: Float, matchType: String) = play.mvc.Results.TODO
}
