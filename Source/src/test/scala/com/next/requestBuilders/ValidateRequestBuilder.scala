 package com.next.requestBuilders

 import com.next.config.Configuration._
 import com.next.config.UuidFeeder
 import io.gatling.core.Predef._
 import io.gatling.http.Predef._
 import io.gatling.http.request.builder.HttpRequestBuilder

 class ValidateRequestBuilder {
   def Build() : HttpRequestBuilder = {

     val _request = http(s"Post Validate")
      .post(baseUrl + s"/Validate")
      .header("Api-Key", apiKey)
      .header("X-Correlation-ID", "#{uuid}")
      .body(ElFileBody("data//Validate.json")).asJson
      .check(status is 200)

     return _request
  }
 }