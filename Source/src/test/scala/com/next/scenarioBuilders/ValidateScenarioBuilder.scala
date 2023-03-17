package com.next.scenarioBuilders

import com.next.requestBuilders._
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.next.config.UuidFeeder

class ValidateScenarioBuilder {
  def Build(): ScenarioBuilder = {
    return scenario("Validate Request")
      .feed(UuidFeeder.feeder)
      .exec(new ValidateRequestBuilder().Build())
  }
}