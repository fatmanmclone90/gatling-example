package com.next.requestBuilders

import com.next.config.Configuration._
import com.next.config.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

class RequestBuilder {
  def Build(filename: String, route: String): HttpRequestBuilder = {
    return http(s"Post $filename")
      .post(baseUrl + s"/$route")
      .header("Api-Key", apiKey)
      .header("X-Correlation-ID", "#{uuid}")
      .body(ElFileBody(s"data//$filename.json"))
      .asJson
      .check(status is 200)
  }
}
