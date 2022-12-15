import java.util.Date

fun main() {
//Primeros pasos con Kotlin
    println("Hello world")

    //Tipos de variables
    // Inmutables: aquellas que no se pueden re asignar
    val inmutable: String = "Alexis"
    //inmutable = "Fabrizzio"; //no se puede re asignar

    //Mutables: aquellas que se pueden re asignar
    var mutable: String = "Alexis"
    mutable = "Fabrizzio"

    // val > var

    //Sintaxis Duck typing
    //"""" -> String
    //'' -> Char
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12
    ejemploVariable.trim();

    //Variables primitivas
    val nombreProfesor: String = "Alexis Vizuete"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    //Clases JAVA
    val fechaNacimiento: Date = Date()

    //CONDICIONALES
    //If-else
    if (true) {
    } else {
    }

    //Switch no existe pero se puede usar el when
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }

        "C" -> {
            println("Alejarse")
        }

        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    //IF-ELSE en una sola linea
    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"

    //FUNCIONES
    //void imprimirNombre(String nombre){}
    //Unit == void

    fun imprimirNombre(nombre: String): Unit {
        println("nombre: ${nombre}");
    }

    fun calcularSueldo(
        sueldo: Double, // Requerido
        tasa: Double = 12.00,   // Opcional (Defecto)
        bonoEspecial: Double? = null,   //Opcional (Null) -> nullable
    ): Double {
        // String -> String? (Puede ser Null)
        // Int -> Int?
        // Date -> Date?
        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }
    }

    //LLAMADO A CLASES CREADAS
    val sumaUno = Suma(  1,  1);
    val sumaDos = Suma(  null,  1);
    val sumaTres = Suma(  1,  null);
    val sumaCuatro = Suma(  null,  null);

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    Suma.pi
    Suma.elevarAlCuadrado( 2)
    Suma.historialsumas

    /*---------------------------------ARREGLOS-----------------------------------------*/
//Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

//Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 8, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

//Operadores -> Sirven para 1s anregtos estéticos y dindxicos


//FOR EACH -> Unit
//Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
                valorActual: Int ->
            println("Valor actual:  ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")

        }
    println(respuestaForEach)



// MAP -> Muta el arregto (Cambio el arreglo)
// 1) Enviamos el nuevo valor de la iteracion
// 2) Nos devuelve es un NUEVO ARREGLO con Los valores modificodos

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual . toDouble () + 100.00
        }
    println(respuestaMap)

    //Sintaxis mas corta
    val respuestaMapDos = arregloDinamico.map {it + 15}
    println(respuestaMapDos)

/* ------------------------------------CLASE 10--------------------------------------------- */
    //Filter -> filtra un arreglo y devuelve una condición
    //1.
    //2.
    val respuestaFilter : List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 //Condicion
            return@filter mayoresACinco
        }
    println(respuestaFilter)

    //Sintaxis mas corta
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilterDos)

    //OR - AND
    //OR any (alguno cumple)
    //AND all (todos cumplen)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all{valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false

    //REDUCE -> valor acumulado
    //valor acumulado = 0
    //se puede escoger como acumular

    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> siempre empieza en 0
            acumulado: Int, valorActual:Int ->
            return@reduce (acumulado + valorActual) //logica de negocio
        }
    println(respuestaReduce) //83

}

/* ------------------------------------CLASES--------------------------------------------- */
    // Clase con constructor
    abstract class NumerosJava {
        protected val numeroUno: Int
        private val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ) { // Bloque de codigo constructor
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializado")
        }
    }

    //Clase Kotlin
    abstract class Numeros ( //Constructor PRIMARIO
    //    uno: Int, //Parametro
    //    public var uno: Int, //Propiedad de la clase publica
    //    var uno: Int, // No es necesario usar el public

        protected val numeroUno: Int, //Propiedad de la clase protected
        protected val numeroDos: Int //Propiedad de la clase private
    ) {
    //  protected val numeroUno: Int
    //  var cedula: String ="";
    // public var cedula: String ="";
        init { //Bloque de código - constructro PRIMARIO
            this.numeroUno
            numeroUno
            this.numeroDos
            numeroUno
            println("Inicializando")
        }
    }

    //Clase con varios constructores
    class Suma( // Constructor Primario Suma
        uno: Int, // Parametro
        dos: Int // Parametro
    ): Numeros(uno, dos) {
        init { // Bloque constructor primario
            this.numeroUno
            this.numeroDos
        }
        //Nuleable en el primer parametro
        constructor(// Segundo constructor
            uno: Int?, // Parametro nuleable
            dos: Int // Parametro
        ) : this( // llamada constructor primario
            if (uno == null) 0 else uno,
            dos
        ) {
        }

        //Nuleable el segundo
        constructor(// tercer constructor
            uno: Int, // Parametro
            dos: Int? // Parametro nuleable
        ) : this( // Llamada constructor primario
            uno,
            if (dos == null) 0 else dos
        ) {
        }

        //Dos Nuleables
        constructor(// cuarto constructor
            uno: Int?, // Parametro nuleable
            dos: Int? // Parametro nuleable
        ) : this( // Llamada constructor primario
            if (uno == null) 0 else uno,
            if (dos == null) 0 else dos
        ) {
        }

        //Metodo para sumar y guardar en el array
        public fun sumar(): Int {   //no es necesario poner public
            val total = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }

        companion object { // Atributos y Metodos "Compartidos” entre las instancias
            val pi = 3.14
            fun elevarAlCuadrado(num: Int): Int {
                return num * num
            }
            val historialsumas = arrayListOf<Int>()
            fun agregarHistorial(valorNuevaSuma: Int) {
                historialsumas.add(valorNuevaSuma)
            }
        }
    }



