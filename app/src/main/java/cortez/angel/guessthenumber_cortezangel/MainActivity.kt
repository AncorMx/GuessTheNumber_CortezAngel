package cortez.angel.guessthenumber_cortezangel

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val guessings: TextView = findViewById<TextView>(R.id.tv_guessings)
        val down: Button = findViewById<Button>(R.id.btn_dwn)
        val up: Button = findViewById<Button>(R.id.btn_up)
        val generate: Button = findViewById<Button>(R.id.btn_generate)
        val guessed: Button = findViewById<Button>(R.id.btn_guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else{
                guessings.text = "No puede ser :( me ganaste"
            }
        }

        down.setOnClickListener {
            maxValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else{
                guessings.text = "No puede ser :( me ganaste"
            }
        }

        guessed.setOnClickListener {
            if (!won){
                guessings.text = "Adiviné, tu número es el ".plus(num)
                guessed.text = "Volver a jugar"
                won = true
            } else{
                generate.visibility = View.VISIBLE
                guessings.text = "Tap on generate to start"
                guessed.visibility = View.GONE
                resetValue()
            }

        }

    }

    fun checkingLimits(): Boolean{
        return minValue != maxValue
    }

    fun resetValue(){
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }
}
