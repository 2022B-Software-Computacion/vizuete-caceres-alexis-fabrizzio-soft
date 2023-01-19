import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import kotlin.Exception

fun main(args: Array<String>) {
    /*try {
        val filePath = "C:\\Users\\alevi\\Desktop\\Septimo\\AppMoviles\\Repositorio\\vizuete-caceres-alexis-fabrizzio-soft\\00-kotlin\\ProyectoIB\\src\\main\\resources\\test.csv"
        var lines:List<String> = File(filePath).readLines()
        lines.forEach { line -> println(line) }
    }catch (e:Exception){
        e.printStackTrace()
    }*/


    val filePath = "C:\\Users\\alevi\\Desktop\\Septimo\\AppMoviles\\Repositorio\\vizuete-caceres-alexis-fabrizzio-soft\\00-kotlin\\ProyectoIB\\src\\main\\resources\\1.csv"
    val file = File(filePath)
    val fileWriter = FileWriter(file)
    val printWriter = PrintWriter(fileWriter)

    val lines: List<String> = file.readLines()
    lines.forEach { line -> println(line) }


    val hola = ArrayList<String>()
    hola.add("hola")
    hola.add("hola")
    hola.add("hola")


    fileWriter.write(hola.toString())
    fileWriter.close()


    lines.forEach { line -> println(line) }


}