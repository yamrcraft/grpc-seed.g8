package $package$.services

import $package$.$service_name;format="camel"$._
import $package$.Settings

import scala.concurrent.Future

class $service_class_name$(settings: Settings) extends $service_class_name$Grpc.$service_class_name$ {

  def $service_method_name;format="camel"$(request: $service_method_name;format="Camel"$Request): Future[$service_method_name;format="Camel"$Response] = {
    val result = $service_method_name;format="Camel"$Response()
    Future.successful(result)
  }

}
