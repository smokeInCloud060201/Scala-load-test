package galvaltron.simulator

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import scalaj.http.Http
import scala.concurrent.duration.DurationInt

class DashboardSimulator extends Simulation {
  val response = Http("https://identity-qa.spdigital-nonprod.auth0.com/oauth/token")
    .postForm(Seq(
      "grant_type" -> "password",
      "username" -> "mock.hos1@yopmail.com",
      "password" -> "Abcd1234",
      "audience" -> "https://profile.qa.up.spdigital.sg/",
      "scope" -> "openid profile email read:contract read:company",
      "client_id" -> "A9qjaQmOsC4OECo70cNx4xjl03BvaGGR"
    ))
    .header("Content-Type", "application/x-www-form-urlencoded")
    .asString

  val token = "Bearer " + ujson.read(response.body)("access_token").str
  println(s"Access Token from TokenManger: $token")

  val httpProtocol = http.baseUrl("https://mera.api.sandbox.spdigital.sg")
    .acceptHeader("*/*")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")


  val getDashBoard = http("Get My Inspection")
    .post("/galvatron/v1/dashboard/my-inspection")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 16:08:49+08:00",
    "filter_type": "MONTH",
    "sortList": []
}"""))
    .check(status.is(200))

  val scn = scenario("Get My Inspection")
      .exec(getDashBoard)

    setUp(
      scn.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(100).during(30.seconds))
    ).protocols(httpProtocol)
}
