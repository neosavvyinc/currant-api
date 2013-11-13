package com.currant.sport

import play.api.db.DB
import scala.concurrent._
import anorm.Sql
import play.api.Play.current
import anorm.SqlParser._
import anorm.SqlRow
import com.currant.model.Auditable
import com.currant.base.Logging
import play.Logger

case class SportCreateRequest(name: String,
                              description: String,
                              active: Boolean,
                              imageUrl: Option[String] = None,
                              minPlayers: Option[Int] = None,
                              maxPlayers: Option[Int] = None,
                              waitList: Option[Int] = None)

case class Sport(id: Long,
                 name: String,
                 description: String,
                 active: Boolean,
                 imageUrl: Option[String],
                 minPlayers: Option[Int],
                 maxPlayers: Option[Int],
                 waitList: Option[Int])


object Sport extends Logging {

  import SportQueries._

  private def fromCreate(id: Long, req: SportCreateRequest) = Sport(id, req.name, req.description, req.active, req.imageUrl, req.minPlayers, req.maxPlayers, req.waitList)

  private def fromRow(row: SqlRow): Sport = {
    Sport(row[Long]("sport_id"),
      row[String]("label"),
      row[String]("description"),
      row[Boolean]("active"),
      row[Option[String]]("image_url"),
      row[Option[Int]]("min_players"),
      row[Option[Int]]("max_players"),
      row[Option[Int]]("wait_list_amount"))
  }


  def create(req: Auditable[SportCreateRequest])(implicit ec: ExecutionContext): Future[Sport] = {
    future {
      DB.withTransaction(implicit c => {
        val id: Long = Sql.sql(insertQ(req.payload)).executeInsert(scalar[Long].single)
        fromCreate(id, req.payload)
      })
    }
  }

  def getAll(implicit ec: ExecutionContext): Future[Seq[Sport]] = {
    future {
      DB.withConnection(implicit c => {
        val results = Sql.sql(sport).asSimple()
        Logger.debug(s"found ${results.size} results")
        results.map(fromRow(_)).toList
      })
    }
  }

  def update(sport: Sport)(implicit ec: ExecutionContext): Future[Sport] = {
    future {
      DB.withTransaction(implicit c => {
        Sql.sql(updateQ(sport)).executeUpdate()
        sport
      })
    }
  }

}

object SportQueries {

  val table = "SPORT"

  val sport = s"""
          | SELECT sport_id, label, description, active, image_url, min_players, max_players, wait_list_amount
          | FROM
          | SPORT
        """.stripMargin

  def insertQ(cr: SportCreateRequest) = {
    s"""
      | INSERT INTO SPORT(label, description, active, image_url, min_players, max_players, wait_list_amount)
      | VALUES
      | ('${cr.name}','${cr.description}',${cr.active},${cr.imageUrl.orNull},${cr.minPlayers.orNull},${cr.maxPlayers.orNull}, ${cr.waitList.orNull})
    """.stripMargin
  }

  def updateQ(s: Sport) = {
    s"""
        | UPDATE SPORT SET
        | label = '${s.name}',
        | description = '${s.description}',
        | active = ${s.active},
        | image_url = '${s.imageUrl.orNull}',
        | min_players = ${s.minPlayers.orNull},
        | max_players = ${s.maxPlayers.orNull},
        | wait_list_amount = ${s.waitList.orNull}
        | WHERE sport_id = ${s.id}
      """.stripMargin
  }
}
