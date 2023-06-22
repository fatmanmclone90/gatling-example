package com.example.scenarios

import com.example.requestBuilders._
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.example.config.UuidFeeder

object ValidateScenarios {
  val messageType = "Validate"
  val defaultScenario = scenario(s"$messageType Request")
    .feed(UuidFeeder.feeder)
    .exec(
      new RequestBuilder()
        .Build(messageType, s"/$messageType")
    )
}