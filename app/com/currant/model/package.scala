package com.currant

import org.joda.time.{DateTimeZone, LocalDate}

package object model {

  def now = new LocalDate(DateTimeZone.UTC)

  case class Auditable[T](payload: T, createdBy: String, createdOn: LocalDate = now)

  case class CurrantException(code: Int, message: String = "", cause: Throwable = null) extends Exception(message, cause)


}
