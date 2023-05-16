package com.example.simulations

import com.example.config.Configuration._
import com.example.scenarios._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoadTest extends Simulation {
  val _numberOfMessages: Int = numberOfMessages;
  val _durationSeconds: Int = durationSeconds;
  val _baseUrl: String = baseUrl;

  def printVariables() = {
    println(s"Base URL $baseUrl");
    println(s"Duration Minutes $durationSeconds");
    println(s"Number of Messages $numberOfMessages");
  }

  printVariables();

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
    .protocols(http.baseUrl(_baseUrl).shareConnections.disableFollowRedirect)
    .assertions(global.responseTime.percentile(95).lt(1000))
    .assertions(global.failedRequests.percent.lt(1))
    .assertions(details("Map Group").responseTime.percentile(95).lt(100))
}
