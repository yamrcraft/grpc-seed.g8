package $package$

import $package$.server.Server
import $package$.services.$service_class_name$
import $package$.$service_name;format="camel"$.$service_class_name$Grpc
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

object ServerApp {

  def main(args: Array[String]): Unit = {

    // reads config
    val config = {
      if (args.isEmpty) {
        ConfigFactory.load()
      } else {
        ConfigFactory.parseString(Source.fromFile(args(0)).mkString)
      }
    }
    val settings = new Settings(config)

    val serverServiceDefinition = $service_class_name$Grpc.bindService(new $service_class_name$(settings), ExecutionContext.global)
    val server = new Server(serverServiceDefinition)
    server.start()
    server.blockUntilShutdown()
  }

}
