package com.example.requestBuilders

import com.example.config.Configuration._
import com.example.config.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

class RequestBuilder {
  def Build(filename: String, route: String): HttpRequestBuilder = {
    return http(s"Post $filename")
      .post(route)
      .header("Api-Key", apiKey)
      .header("X-Correlation-ID", "#{uuid}")
      .body(ElFileBody(s"data//$filename.json"))
      .asJson
      .check(status is 200)
  }
}
