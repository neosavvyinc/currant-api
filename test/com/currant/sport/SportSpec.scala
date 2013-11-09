package com.currant.sport

import play.api.test.{FakeHeaders, FakeRequest, WithApplication, PlaySpecification}
import play.api.libs.json.Json
import com.currant.model.SportCreateRequest

class SportSpec extends PlaySpecification {

  implicit val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportCreateWrites = Json.writes[SportCreateRequest]


  "Sports" should {

    "respond to the create Action" in new WithApplication {
      val fr = FakeRequest(POST, "/sport").withJsonBody(Json.toJson(SportCreateRequest("baseball", "baseball", true)))
      val result = com.currant.sport.SportController.create(fr)

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      val str = contentAsString(result) must contain("It works!")
    }


  }

}