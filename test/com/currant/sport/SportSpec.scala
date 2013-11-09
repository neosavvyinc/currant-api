package com.currant.sport

import play.api.test.{FakeHeaders, FakeRequest, WithApplication, PlaySpecification}
import play.api.libs.json.Json
import com.currant.model.SportCreateRequest
import play.mvc.Http.HeaderNames
import play.api.http.MimeTypes

class SportSpec extends PlaySpecification {

  implicit val sportCreateReads = Json.reads[SportCreateRequest]
  implicit val sportCreateWrites = Json.writes[SportCreateRequest]


  "Sports" should {

    "respond to the create Action" in new WithApplication {
      val fr = FakeRequest(POST, "/sport")
        .withHeaders(HeaderNames.CONTENT_TYPE -> MimeTypes.JSON)
        .withJsonBody(Json.toJson(SportCreateRequest("baseball", "baseball", true)))
      val result = com.currant.sport.SportController.create(fr)

      status(result) must equalTo(OK)
      contentType(result) must beSome(MimeTypes.JSON)
      val str = contentAsString(result)
      print(str);
    }


  }

}