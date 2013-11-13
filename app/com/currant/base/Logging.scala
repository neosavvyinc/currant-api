package com.currant.base

import org.slf4j.LoggerFactory


trait Logging {self =>
  @transient
  final protected[this] val log = LoggerFactory.getLogger(self.getClass.getName)

}