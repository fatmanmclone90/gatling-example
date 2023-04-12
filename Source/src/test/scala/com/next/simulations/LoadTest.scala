package com.next.simulations

import com.next.config.Configuration._
import com.next.scenarios._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoadTest extends Simulation {
  val _numberOfMessages: Int = numberOfMessages;
  val _durationSeconds: Int = durationSeconds;
  
  // https://gatling.io/docs/gatling/reference/current/core/injection/
   private val _validateSimulation = ValidateScenarios.defaultScenario
    .inject(
      rampUsers(_numberOfMessages)
      .during(_durationSeconds)
    )

    private val _mapSimulation = MapScenarios.defaultScenario
    .inject(
      rampUsers(_numberOfMessages)
      .during(_durationSeconds)
    )

  // https://gatling.io/docs/gatling/reference/current/core/assertions/
  setUp(_validateSimulation, _mapSimulation)
    .assertions(global.responseTime.percentile(95).lt(1000))
    .assertions(global.failedRequests.percent.lt(1))
}