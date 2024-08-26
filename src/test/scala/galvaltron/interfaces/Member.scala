package galvaltron.interfaces

case class Member(group_id: String,
                  start_time: String,
                  end_time: String,
                  page: Int,
                  size: Int,
                  sortList: List[String])
