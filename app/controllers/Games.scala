package controllers

import play.api.mvc._

object Games extends Controller {
  def find(gameType: String, location: String) = play.mvc.Results.TODO

  def findClubsForMember() = play.mvc.Results.TODO

  def create() = play.mvc.Results.TODO

  def update(id: Float) = play.mvc.Results.TODO

  def delete(id: Float) = play.mvc.Results.TODO

  def findPlayers(id: Float) = play.mvc.Results.TODO

  def updatePlayers(gameId: Float) = play.mvc.Results.TODO

  def createEquipment() = play.mvc.Results.TODO

  def findGames(lat: Float, long: Float, sportId: Float, visibility: String) = play.mvc.Results.TODO

  def findGamesForClubId() = play.mvc.Results.TODO

  def updateGameProfileConnection() = play.mvc.Results.TODO
}