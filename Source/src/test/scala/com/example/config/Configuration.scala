package com.example.config

//This is where you set configuration values for the project
object Configuration {
  lazy val baseUrl: String = System.getProperty("baseUrl")
  lazy val numberOfMessages: Int = Integer.getInteger("numberOfMessages", 10).toInt
  lazy val durationSeconds: Int = Integer.getInteger("durationSeconds", 10).toInt
  lazy val apiKey: String = System.getProperty("apiKey")
}
