package app.console

import app.console.controllers.ConsoleController
import app.console.views.ConsoleView

/**
 * Some code in this project is inspired by BI-OOP lab
 * */
object Main extends App {
  val controller = new ConsoleController
  val view = new ConsoleView(args, controller)
  view.run()
}

/*
* Supported program arguments:
* Load image
    * --image relative/or/absolute/path.jpg   //supports jpg, jpeg, png
    * --image-random                          //loads random image
* Export image
    * --output-console
    * --output-file relative/or/absolute/path
* Filter image
    * --scale X                               //supports X from (1, 0.25, 4)
    * --rotate X                              //supports X multiple of 90
    * --flip X                                //supports X from (x, y)
* */
