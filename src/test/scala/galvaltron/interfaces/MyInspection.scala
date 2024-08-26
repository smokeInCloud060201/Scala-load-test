package galvaltron.interfaces

case class MyInspection(group_id: String,
                        start_time: String,
                        end_time: String,
                        filter_type: String,
                        sortList: List[String])

