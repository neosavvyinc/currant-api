package com.currant.sport

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
/**
 * Created by Neosavvy
 *
 * User: adamparrish
 * Date: 11/14/13
 * Time: 10:08 PM
 */
class SimpleSpec extends Specification {

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      "Hello world" must have size(11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }

  "Computer model" should {

    "be retrieved by id" in new WithApplication {
      val anyValue = SportController.create()
    }
  }

}