package com.miriarte.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.miriarte.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var resultado = 0.0
    private var operacion: String? = null
    private var principio = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        operacion = null

        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnComa.setOnClickListener(this)
        binding.btnDivid.setOnClickListener(this)
        binding.btnMulti.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnMenos.setOnClickListener(this)
        binding.btnIgual.setOnClickListener(this)
        binding.btnLimpiar.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view){
            binding.btn0 -> numeroPresionado("0")
            binding.btn1 -> numeroPresionado("1")
            binding.btn2 -> numeroPresionado("2")
            binding.btn3 -> numeroPresionado("3")
            binding.btn4 -> numeroPresionado("4")
            binding.btn5 -> numeroPresionado("5")
            binding.btn6 -> numeroPresionado("6")
            binding.btn7 -> numeroPresionado("7")
            binding.btn8 -> numeroPresionado("8")
            binding.btn9 -> numeroPresionado("9")
            binding.btnComa -> numeroPresionado(".")
            binding.btnDivid -> operacionPresionada("/")
            binding.btnMulti -> operacionPresionada("*")
            binding.btnPlus -> operacionPresionada("+")
            binding.btnMenos -> operacionPresionada("-")
            binding.btnIgual -> igualPresionado(binding.pantalla.text.toString().toDouble())
            binding.btnLimpiar -> limpiarPresionado()
        }
    }

    private fun numeroPresionado(numero: String){
        renderPantalla(numero)
    }

    private fun renderPantalla(numero: String){
        if(principio){
            binding.pantalla.text = ""
            principio = false
        }
        if(numero == "." && binding.pantalla.text.contains(".")){
            binding.pantalla.text = "${binding.pantalla.text}"
        }else
            binding.pantalla.text = "${binding.pantalla.text}$numero"
    }

    private fun operacionPresionada(operacion: String){
        calcular(binding.pantalla.text.toString().toDouble())
        this.operacion = operacion
        principio = true
    }

    private fun calcular(numero: Double){

        when(operacion){
            "+" -> resultado += numero
            "-" -> resultado -= numero
            "/" -> resultado /= numero
            "*" -> resultado *= numero
            else -> resultado = numero
        }
        binding.pantalla.text = if(resultado.toString().endsWith(".0"))
            resultado.toString().replace(".0", "")
        else
            resultado.toString()
    }

    private fun igualPresionado(numero: Double){
        when(operacion){
            "+" -> resultado += numero
            "-" -> resultado -= numero
            "/" -> resultado /= numero
            "*" -> resultado *= numero
            else -> resultado = 0.0
        }
        binding.pantalla.text = if(resultado.toString().endsWith(".0"))
            resultado.toString().replace(".0", "")
        else
            "%.2f".format(resultado)

        operacion = null
        principio = true
    }

    private fun limpiarPresionado(){
        binding.pantalla.text = "0"
        resultado = 0.0
        operacion = null
        principio = true
    }
}