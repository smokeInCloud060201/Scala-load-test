package galvaltron.utils

import galvaltron.interfaces._
import io.circe.Encoder

object Encoders {
  implicit val myInspectionEncoder: Encoder[MyInspection] = Encoder.forProduct5(
    "group_id",
    "start_time",
    "end_time",
    "filter_type",
    "sortList"
  )(mi => (mi.group_id, mi.start_time, mi.end_time, mi.filter_type, mi.sortList))

  implicit val memberInspectionEncoder: Encoder[MemberInspection] = Encoder.forProduct3(
    "group_id",
    "start_time",
    "end_time"
  )(mi => (mi.group_id, mi.start_time, mi.end_time))

  implicit val memberEncoder: Encoder[Member] = Encoder.forProduct6(
    "group_id",
    "start_time",
    "end_time",
    "page",
    "size",
    "sortList"
  )(m => (m.group_id, m.start_time, m.end_time, m.page, m.size, m.sortList))

  implicit val stopWorkEncoder: Encoder[StopWork] = Encoder.forProduct7(
    "group_id",
    "group_type",
    "start_time",
    "end_time",
    "page",
    "size",
    "sortList"
  )(sw => (sw.group_id, sw.group_type, sw.start_time, sw.end_time, sw.page, sw.size, sw.sortList))

  implicit val inspectionsEncoder: Encoder[Inspections] = Encoder.forProduct6(
    "group_id",
    "start_time",
    "end_time",
    "page",
    "size",
    "sortList"
  )(i => (i.group_id, i.start_time, i.end_time, i.page, i.size, i.sortList))

  implicit val chartEncoder: Encoder[Chart] = Encoder.forProduct6(
    "group_id",
    "start_time",
    "end_time",
    "filter_type",
    "page",
    "size"
  )(c => (c.group_id, c.start_time, c.end_time, c.filter_type, c.page, c.size))

  implicit val dataEncoder: Encoder[Data] = Encoder.forProduct6(
    "myInspection",
    "memberInspection",
    "member",
    "stopWork",
    "inspections",
    "chart"
  )(d => (d.myInspection, d.memberInspection, d.member, d.stopWork, d.inspections, d.chart))
}
