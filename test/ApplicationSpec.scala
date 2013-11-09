import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class AuthenticationSpec extends PlaySpecification {

  "Authentication" should {

    "respond to the login Action" in new WithApplication {
      val result = controllers.Authentication.login()(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      contentAsString(result) must contain("It works!")
    }


  }
}
