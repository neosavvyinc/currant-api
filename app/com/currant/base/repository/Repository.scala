package com.currant.base.repository

import scala.concurrent.Future

/**
 * User: nsauro
 * Date: 11/13/13
 * Time: 10:22 PM
 */

trait Repository[E, CR, UR, D] {

  def create(c : CR) : Future[E]

  def update(u : UR) : Future[E]

  def read(query : String) : Future[Seq[E]]

  def readOne(query : String) : Future[E]

  def delete(ids : Seq[D]) : Future[Int]

}

trait AnormRepository[E, CR, UR, D] extends Repository[E, CR, UR, D] {




  def create(c: CR): Future[E]  = ???

  def update(u: UR): Future[E] = ???

  def read(query: String): Future[Seq[E]] = ???

  def readOne(query: String): Future[E] = ???

  def delete(ids: Seq[D]): Future[Int] = ???
}