package $package$.server

import com.typesafe.config.ConfigFactory
import io.grpc.{ServerBuilder, ServerServiceDefinition}
import org.apache.logging.log4j.LogManager

class Server(serviceDefinitions: ServerServiceDefinition*) {

  implicit val logger = LogManager.getLogger(getClass)

  private val config     = ConfigFactory.load()
  private val serverPort = config.getInt("$name;format="normalize"$.server.port")

  private var server: Option[io.grpc.Server] = None

  def start(): Unit = {
    val serverBuilder = ServerBuilder.forPort(serverPort)
    serviceDefinitions.foreach(serverBuilder.addService(_))

    server = Option(
      serverBuilder
        .build
        .start
    )
    logger.info(s"\${this.getClass.getName} started. Listening on port \$serverPort")

    // make sure server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = stopServer()
    })
  }

  private def stopServer(): Unit = server.foreach(_.shutdown())

  def blockUntilShutdown(): Unit =
    server.foreach(_.awaitTermination())

}
