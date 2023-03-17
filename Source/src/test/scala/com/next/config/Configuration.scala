package com.next.config

//This is where you set configuration values for the project
object Configuration {
  lazy val baseUrl: String = System.getProperty("baseUrl")
  lazy val numberOfMessages: Int = Integer.getInteger("numberOfMessages", 10).toInt
  lazy val durationSeconds: Int = Integer.getInteger("durationSeconds", 10).toInt
  lazy val overallDuration: Double = System.getProperty("overallDuration").toDouble
  lazy val headerCode: String = System.getProperty("headerCode")
  lazy val apiKey: String = System.getProperty("apiKey")
}
