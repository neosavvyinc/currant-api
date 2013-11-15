package com.currant.game

import com.currant.base.CurrantController
import play.api.mvc._

object GamesController extends CurrantController {
  def find(gameType: String, location: String) = play.mvc.Results.TODO

  def findClubsForMember(currantProfileId: Float ) = play.mvc.Results.TODO

  def create() = play.mvc.Results.TODO

  def update(id: Float) = play.mvc.Results.TODO

  def delete(id: Float) = play.mvc.Results.TODO

  def findPlayers(id: Float) = play.mvc.Results.TODO

  def updatePlayers(gameId: Float) = play.mvc.Results.TODO

  def createCustomEquipment(gameId: Float) = play.mvc.Results.TODO

  def findGames(gameType: String, lat: Float, long: Float, sportId: Float, visibility: String) = play.mvc.Results.TODO

  def findGamesForClubId( clubId: Float ) = play.mvc.Results.TODO

  def updateGameProfileConnection(gameId:Float, status: String, currantUserId: Float ) = play.mvc.Results.TODO
}
