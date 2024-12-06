
import rx.lang.scala.Observable
import scala.util.Random
import scala.concurrent.duration._

object School extends App {


  //Sensores con intervalos 1 segundo y 1.5 segundos
  val sensor1 = Observable.interval(1.second)
    .map(_ => Random.between(20, 30))

  //Sensor de 1.5 segundos. Emite valores periodicamente entre 30 y 40
  val sensor2 = Observable.interval(1.5.second)
    .map(_ => Random.between(30, 40))

  //Detener el flujo de ambos sensores después de 10 pares de

  //Subscribe: Procesa los valores emitidos por los temporizadores 1 y 2
  sensor1.zip(sensor2).take(10) .subscribe({ case (temp1, temp2) =>
    val diferencia = {
      Math.abs(temp1 - temp2) //Calcular la diferencia de ambas temperaturas
    }


    println(s"Sensor1: $temp1°C, Sensor2: $temp2°C, Difference: $diferencia°C")

    if (diferencia >= 6) {
      println(s"🚨ALERT! Significant difference detected:: $diferencia°C")
    }
  },
    e => println(s"Error: ${e.getMessage}"),
    () => println("Stream completed")
  )
  Thread.sleep(11000)
}