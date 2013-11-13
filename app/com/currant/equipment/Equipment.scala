package com.currant.equipment


case class EquipmentCreateRequest(name: String,
                                   description: String,
                                   active: Boolean,
                                   sports: Seq[Long],
                                   imageUrl: Option[String])

case class Equipment(id: Long, name: String,
                      description: String, active: Boolean,
                      sports: Seq[Long],
                      imageUrl: Option[String])
