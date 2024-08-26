package galvaltron.interfaces

case class Chart(
                  group_id: String,
                  start_time: String,
                  end_time: String,
                  filter_type: String,
                  page: Int,
                  size: Int
                )
