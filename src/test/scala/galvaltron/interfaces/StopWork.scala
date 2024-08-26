package galvaltron.interfaces

case class StopWork(
                     group_id: String,
                    group_type: String,
                    start_time: String,
                    end_time: String,
                    page: Int,
                    size: Int,
                    sortList: List[String]
                   )
