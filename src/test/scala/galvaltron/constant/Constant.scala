package galvaltron.constant

object Constant {
  object RequestHeader {
    val GRANT_TYPE_KEY = "grant_type"
    val USERNAME_KEY = "username"
    val PASSWORD_KEY = "password"
    val AUDIENCE_KEY = "audience"
    val SCOPE_KEY = "scope"
    val CLIENT_ID_KEY = "client_id"
    val CONTENT_TYPE_KEY = "Content-Type"

    val GRANT_TYPE = "password"
    val USERNAME = "mock.hos1@yopmail.com"
    val PASSWORD = "Abcd1234"
    val AUDIENCE = "https://profile.qa.up.spdigital.sg/"
    val SCOPE = "openid profile email read:contract read:company"
    val CLIENT_ID = "A9qjaQmOsC4OECo70cNx4xjl03BvaGGR"
    val CONTENT_TYPE_FORM = "application/x-www-form-urlencoded"
    val CONTENT_TYPE_JSON = "application/json"
  }
  
  val API_HOST = "https://mera.api.sandbox.spdigital.sg"
}
