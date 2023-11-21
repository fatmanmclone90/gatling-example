package com.example.helpers

import java.util.{Calendar, Date}
import java.text.SimpleDateFormat

object UserAgentHelper {

  def get(base: String): String = {
    val dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    val cal = Calendar.getInstance();
    return base + (dateFormat.format(cal.getTime()));
  }
}
