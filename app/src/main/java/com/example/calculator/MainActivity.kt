package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var ultimoNumero = false
    var ultimoPunto = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //var contador = 0  <-- Code hace parte de la novita
    fun onDigit(view: View){
        //Toast.makeText(this,"Buton work",Toast.LENGTH_SHORT).show()
        /*Code Para la novita
        val arrayList = ArrayList<String>()
        arrayList.addAll(listOf("Te ","Amo ","Mi ","Vida ","❤"))

        //se va agregando!!
        val myTV = findViewById<TextView>(R.id.TvInput)
        myTV.text = ""
        if (contador > 4){
            contador=0
        }else{
            myTV.append(arrayList[contador])
            contador += 1
        }*/
        val myTV = findViewById<TextView>(R.id.TvInput)
        myTV.append((view as Button).text)
        ultimoNumero = true

        //if (myTV.text.contains("1")) myTV.text = "hola"

    }

    fun onClear(view: View){
        val myTV = findViewById<TextView>(R.id.TvInput)
        myTV.text = ""
        ultimoPunto = false
        ultimoNumero = false
    }

    fun onDecimalPoint(view: View){
        if (ultimoNumero && !ultimoPunto){
            //println("ultimoNumero -->$ultimoNumero//true y ultimoPunto -->${!ultimoPunto}//true y $ultimoPunto//false")
            val myTV = findViewById<TextView>(R.id.TvInput)
            myTV.append(".")
            ultimoNumero = false
            ultimoPunto = true
        }
    }
    fun onEqual(view: View){
        val myTV = findViewById<TextView>(R.id.TvInput)
        if (ultimoNumero){
            //rescatamos el valor y lo guardamos en la variable tvValue
            var tvValue = myTV.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    //El substring devuelve una subcadena que comienza en el indice indicado hasta el final del string
                    tvValue = tvValue.substring(1)
                    println("tvValue -->$tvValue")
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")//
                    println("splitValue-> $splitValue")//[10,20]
                    var one = splitValue[0]//10??
                    println("one-> $one")
                    var two = splitValue[1]//20
                    println("two-> $two")
                    //10-20
                    if (!prefix.isEmpty()) one = prefix + one//Aqui pasamos el one a negativo
                    //println("oneDespues -> $one")

                    myTV.text = removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())

                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")//
                    println("splitValue-> $splitValue")//[10,20]
                    var one = splitValue[0]//10??
                    println("one-> $one")
                    var two = splitValue[1]//20
                    println("two-> $two")
                    //10-20
                    if (!prefix.isEmpty()) one = prefix + one//Aqui pasamos el one a negativo
                    //println("oneDespues -> $one")

                    myTV.text = removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())

                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")//
                    println("splitValue-> $splitValue")//[10,20]
                    var one = splitValue[0]//10??
                    println("one-> $one")
                    var two = splitValue[1]//20
                    println("two-> $two")
                    //10-20
                    if (!prefix.isEmpty()) one = prefix + one//Aqui pasamos el one a negativo
                    //println("oneDespues -> $one")

                    myTV.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")//
                    println("splitValue-> $splitValue")//[10,20]
                    var one = splitValue[0]//10??
                    println("one-> $one")
                    var two = splitValue[1]//20
                    println("two-> $two")
                    //10-20
                    if (!prefix.isEmpty()) one = prefix + one//Aqui pasamos el one a negativo
                    //println("oneDespues -> $one")

                    myTV.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String): String{
        var value = result
        if (result.contains(".0")) value = result.substring(0,result.length - 2)
        return value//4.0 -> 4
    }

    fun onOperator(view: View) {
        val myTV = findViewById<TextView>(R.id.TvInput)
        //isOperatorAdded(myTV.text.toString())
        //println("ultimoNumero ->$ultimoNumero y !isOperatorAdded(myTV.text.toString())-> ${!isOperatorAdded(myTV.text.toString())}")
        if (ultimoNumero && !isOperatorAdded(myTV.text.toString())) {
            myTV.append((view as Button).text)
            ultimoNumero = false
            ultimoPunto = false
        }
    }

    private fun isOperatorAdded(value: String):Boolean {
        //println("value--> $value")
        //println("value.startsWith--> ${value.startsWith("-")}")
        //return (value.startsWith("-")) //podemos dejar esta linea tambien pero tendriamos que tener encuenta la ley de signos
        //else value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        return if (value.startsWith("-")) false
        else value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
    }
}