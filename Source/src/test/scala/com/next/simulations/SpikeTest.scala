package com.example.simulations

import com.example.config.Configuration._
import com.example.scenarios._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SpikeTest extends Simulation {

  val _numberOfMessages: Int = numberOfMessages;
  val _durationSeconds: Int = durationSeconds;
  val _baseUrl: String = baseUrl;

  val peakMultiplier: Int = 2;
  val maxWaitTime: Int = 30;
  val r = new scala.util.Random;
  val activePeriod: Int = 60;
  val numberOfPeriods = (_durationSeconds / 60).toInt;
  val restPeriod = (activePeriod * peakMultiplier).toInt;

  def printVariables() = {
    println(s"rest period $restPeriod");
    println(s"number of periods $numberOfPeriods");
    println(s"peak multiplier $peakMultiplier");
  }

  def loadPerPeriod(
      expectedLoad: Int,
      peakMultiplier: Double,
      activePeriod: Int
  ): Int =
    (peakMultiplier * (expectedLoad / activePeriod)).toInt;
  
  def peakTestProfile(
      expectedLoad: Int,
      peakMultiplier: Double,
      activePeriod: Int,
      maxWaitTime: Int
  ) =
    Seq(nothingFor(1 + r.nextInt(maxWaitTime)))
      .concat(
        (1 to numberOfPeriods)
          .flatMap(i =>
            Seq(
              rampUsers(
                loadPerPeriod(
                  expectedLoad,
                  peakMultiplier,
                  activePeriod
                )
              ).during(activePeriod),
              nothingFor(restPeriod)
            )
          )
      );

  printVariables();

  private val _mapSimulation = MapScenarios.defaultScenario
    .inject(
      peakTestProfile(
        100,
        peakMultiplier,
        activePeriod,
        maxWaitTime
      )
    );
  
  setUp(_mapSimulation)
    .protocols(http.baseUrl(_baseUrl).shareConnections.disableFollowRedirect)
    .assertions(global.responseTime.percentile(95).lt(1000))
    .assertions(global.failedRequests.percent.lt(1))
}
