package com.currant.sport


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

/*  case class SportUpdateRequest()*/