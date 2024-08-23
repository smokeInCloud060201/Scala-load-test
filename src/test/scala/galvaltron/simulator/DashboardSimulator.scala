package galvaltron.simulator

import galvaltron.constant.Constant.API_HOST
import galvaltron.constant.Constant.RequestHeader._
import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import scalaj.http.Http

import scala.concurrent.duration.DurationInt

class DashboardSimulator extends Simulation {
  val response = Http("https://identity-qa.spdigital-nonprod.auth0.com/oauth/token")
    .postForm(Seq(
      GRANT_TYPE_KEY -> GRANT_TYPE,
      USERNAME_KEY -> USERNAME,
      PASSWORD_KEY -> PASSWORD,
      AUDIENCE_KEY -> AUDIENCE,
      SCOPE_KEY -> SCOPE,
      CLIENT_ID_KEY -> CLIENT_ID
    ))
    .header(CONTENT_TYPE_KEY, CONTENT_TYPE_FORM)
    .asString

  val token = "Bearer " + ujson.read(response.body)("access_token").str
  println(s"Access Token from TokenManger: $token")

  val httpProtocol = http.baseUrl(API_HOST)
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
