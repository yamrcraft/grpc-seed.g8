package $package$.server

import $package$.Settings
import io.grpc.{ServerBuilder, ServerServiceDefinition}
import org.apache.logging.log4j.LogManager

class Server(settings: Settings, serviceDefinitions: ServerServiceDefinition*) {

  implicit val logger = LogManager.getLogger(getClass)

  private var server: Option[io.grpc.Server] = None

  def start(): Unit = {
    val serverBuilder = ServerBuilder.forPort(settings.port)
    serviceDefinitions.foreach(serverBuilder.addService(_))

    server = Option(
      serverBuilder
        .build
        .start
    )
    logger.info(s"\${this.getClass.getName} started. Listening on port \${settings.port}")

    // make sure server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = stopServer()
    })
  }

  private def stopServer(): Unit = server.foreach(_.shutdown())

  def blockUntilShutdown(): Unit =
    server.foreach(_.awaitTermination())

}
