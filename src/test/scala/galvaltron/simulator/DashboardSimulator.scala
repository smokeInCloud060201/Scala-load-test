package galvaltron.simulator

import galvaltron.constant.Constant.{API_HOST, PROFILE_HOST}
import galvaltron.constant.Constant.RequestHeader.*
import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import scalaj.http.Http

import scala.concurrent.duration.DurationInt

class DashboardSimulator extends Simulation {
  val response = Http(PROFILE_HOST + "/oauth/token")
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


  val getMyInspection = http("Get My Inspection")
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

  val getMemberInspection = http("Get Member Inspection")
    .post("/galvatron/v1/dashboard/member-inspection")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 17:56:44+08:00"
}"""))
    .check(status.is(200))

  val getMember= http("Get Member")
    .post("/galvatron/v1/dashboard/member")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 17:56:44+08:00",
    "page": 0,
    "size": 3,
    "sortList": []
}"""))
    .check(status.is(200))

  val getStopWork = http("Get Stop Work")
    .post("/galvatron/v1/dashboard/stop-work")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 17:56:44+08:00",
    "page": 0,
    "size": 3,
    "sortList": []
}"""))
    .check(status.is(200))

  val getInspections = http("Get Inspections")
    .post("/galvatron/v1/dashboard/inspections")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 17:56:44+08:00",
    "page": 0,
    "size": 3,
    "sortList": []
}"""))
    .check(status.is(200))

  val getChart = http("Get Chart")
    .post("/galvatron/v1/dashboard/chart")
    .header("Content-Type", "application/json")
    .header("Authorization", token)
    .body(StringBody(
      """{
    "group_id": "1166212187920691200",
    "start_time": "2024-08-01 01:00:00+08:00",
    "end_time": "2024-08-23 17:56:44+08:00",
    "filter_type": "MONTH",
    "page": 0,
    "size": 20
}"""))
    .check(status.is(200))

  val scnMyInspection = scenario("Get My Inspection")
      .exec(getMyInspection)

  val scnMemberInspection = scenario("Get Member Inspection")
    .exec(getMemberInspection)

  val scnMember = scenario("Get Member")
    .exec(getMember)

  val scnStopWork = scenario("Get Stop Work")
    .exec(getStopWork)

  val scnInspections = scenario("Get Inspections")
    .exec(getInspections)

  val scnChart = scenario("Get Chart")
    .exec(getChart)

    setUp(
      scnMyInspection.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
      scnMemberInspection.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
      scnMember.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
      scnStopWork.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
      scnInspections.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
      scnChart.inject(nothingFor(5) ,atOnceUsers(1), rampUsers(50).during(30.seconds)),
    ).protocols(httpProtocol)
}
