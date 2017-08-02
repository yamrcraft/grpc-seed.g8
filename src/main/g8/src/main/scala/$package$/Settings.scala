package $package$

import com.typesafe.config.Config

class Settings(config: Config) {

  val host = config.getString("$name;format="normalize"$.server.host")
  val port = config.getInt("$name;format="normalize"$.server.port")

}

