package com.example.config

object UuidFeeder {
    val feeder = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString()))
}