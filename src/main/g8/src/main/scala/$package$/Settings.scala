package $package$

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

class Settings(config: Config) {

  case class Server(host: String, port: Int)
  val server = config.as[Server]("$name;format="normalize"$.server")

}

