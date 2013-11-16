package com.currant.sport

import play.api.test.{FakeRequest, PlaySpecification}
import play.api.libs.json.{Json, JsBoolean, JsString, JsObject}
import scala.concurrent.Future
import play.api.mvc.SimpleResult
import play.api.Logger
import com.currant.CurrantApplication

/*Two issues:
  not setting proper headers is NOT breaking this
  more than one test cannot connect to the database.
 */

object SportSpec extends PlaySpecification {
  sequential

  implicit val sportReads = Json.reads[Sport]

  "Sports Controller" should {

    "create sports" in new CurrantApplication {
      val s = createSport("baseball", "pasttime", true)
      s should not beNull
    }
  }

  "SportController 2 " should {
    "update sports" in new CurrantApplication {

      val sport = createSport("football", "gridiron", true)

      val fr = FakeRequest(POST, "/sports").
        withJsonBody(JsObject(Seq("id" -> JsString(sport.id.toString), "name" -> JsString("foozball"), "description" -> JsString("description"), "active" -> JsBoolean(true))))

      val Some(result) = route(fr)

      val s = assertSport("foozball", "description", true, result)
      s should not beNull
    }
  }


  private def createSport(name: String, description: String, active: Boolean): Sport = {
    val fr = FakeRequest(PUT, "/sports").
      withJsonBody(JsObject(Seq("name" -> JsString(name), "description" -> JsString(description), "active" -> JsBoolean(active))))

    val Some(result) = route(fr)
    assertSport(name, description, active, result)

  }

  private def assertSport(name: String, description: String, active: Boolean, result: Future[SimpleResult]): Sport = {
    status(result) must be equalTo OK
    val body = contentAsJson(result) \ "payload"

    Logger.debug(body.toString)
    val sport = body.asOpt[Sport]
    sport should beSome(Sport(1, name, description, active, None, None, None, None))
    sport.get
  }

}