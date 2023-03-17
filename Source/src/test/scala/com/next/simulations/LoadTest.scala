package com.next.simulations

import com.next.config.Configuration._
import com.next.scenarioBuilders._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoadTest extends Simulation {
  val _numberOfMessages: Int = numberOfMessages;
  val _durationSeconds: Int = durationSeconds;
  
  val _mapScenarioBuilder = new MapScenarioBuilder
  private val _mapSimulation = _mapScenarioBuilder.Build()
  .inject(
    rampUsers(_numberOfMessages)
    .during(_durationSeconds)
  )

  val _validateScenarioBuilder = new ValidateScenarioBuilder
  private val _validateSimulation = _validateScenarioBuilder.Build()
  .inject(
    rampUsers(_numberOfMessages)
    .during(_durationSeconds)
  )

  setUp(_mapSimulation, _validateSimulation)
    .assertions(global.responseTime.percentile(95).lt(1000))
    .assertions(global.failedRequests.percent.lt(1))
}
