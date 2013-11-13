package com.currant

package object model {
  
 case class CurrantException(code : Int, message : String = "", cause : Throwable = null) extends Exception(message, cause)


}
