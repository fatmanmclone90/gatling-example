package com.next.scenarios

import com.next.requestBuilders._
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.next.config.UuidFeeder

object ValidateScenarios {
  val messageType = "Validate"
  val defaultScenario = scenario(s"$messageType Request")
    .feed(UuidFeeder.feeder)
    .exec(
      new RequestBuilder()
        .Build(messageType, s"/$messageType")
    )
}