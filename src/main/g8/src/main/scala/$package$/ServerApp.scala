package $package$

import $package$.server.Server
import $package$.services.$service_class_name$
import $package$.$service_name;format="camel"$.$service_class_name$Grpc

import scala.concurrent.ExecutionContext

object ServerApp {

  def main(args: Array[String]): Unit = {
    val serverServiceDefinition = $service_class_name$Grpc.bindService(new $service_class_name$, ExecutionContext.global)
    val server = new Server(serverServiceDefinition)
    server.start()
    server.blockUntilShutdown()
  }

}
