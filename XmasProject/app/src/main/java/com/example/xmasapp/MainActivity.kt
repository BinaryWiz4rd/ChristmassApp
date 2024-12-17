package com.example.xmasapp
import android.content.Intent
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private val greetingHistory = mutableListOf<String>()
    private val giftHistory = mutableListOf<String>()
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val mainLayout = findViewById<GridLayout>(R.id.main)
        val buttonGenerateGreeting =
            findViewById<Button>(R.id.buttonGenerateGreeting)
        val buttonGenerateGift =
            findViewById<Button>(R.id.buttonGenerateGift)
        resultTextView = findViewById(R.id.resultTextView)
        val historyTextView = findViewById<TextView>(R.id.historyTextView)
//https://www.youtube.com/watch?feature=shared&v=IpNLx75b0hE
        Thread{val source = ImageDecoder.createSource(resources,
            R.drawable.renifer_twerk)
            val drawable=ImageDecoder.decodeDrawable(source)
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.post {
                imageView.setImageDrawable(drawable)
//safe cast
                (drawable as? AnimatedImageDrawable)?.start()
            }
        }.start()
        val colors = listOf(
            "#FF0000", "#228B22", "#FFD700", "#B22222", "#006400",
            "#FF6347",
            "#DAA520", "#F0E68C", "#CD5C5C", "#8B0000", "#FF4500",
            "#90EE90"
        ).shuffled()
//chce zeby mozna bylo otworzyc tylko okienko aktualnego dnia jaki
// a pozostale zeby byly zamkniete DODAC KOMUNIKAT ZE ZABLOKOWANY

        val currentDay = LocalDate.now().dayOfMonth
        for (i in 1..24) {
            val button = Button(this).apply {
                text = "$i"
                textSize = 14f
                setBackgroundColor(
                    Color.parseColor(
                        colors[i %
                                colors.size]
                    )
                )
                setTextColor(Color.WHITE)  //wlasnie to co wyzej, ze pozostale dni sa zablokowane
                isEnabled = (i == currentDay)
                setOnClickListener {
                    showRandomGreetingAndGift()  //j
                    disableAllButtons()
                }
            }
            val params = GridLayout.LayoutParams().apply {
                width = 150
                height = 150
                setMargins(8, 8, 8, 8)
            }
            mainLayout.addView(button, params)
        }
        buttonGenerateGreeting.setOnClickListener {
            if (SantasElf.isGrinchTime()) {
                showGrinchSurprise()
            } else {
                val greeting = SantasElf.getRandomGreeting()
                resultTextView.text = greeting
            }
        }
        buttonGenerateGift.setOnClickListener {
            if (SantasElf.isGrinchTime()) {
                showGrinchSurprise()
            } else {
                val gift = SantasElf.getRandomGift()
                resultTextView.text = "Your Gift: $gift"
            }
        }
    }
    private fun showRandomGreetingAndGift() {
        if (SantasElf.isGrinchTime()) {
            showGrinchSurprise()
        } else {
            val greeting = SantasElf.getRandomGreeting()
            val gift = SantasElf.getRandomGift()
            resultTextView.text = "$greeting\nYour Gift: $gift"
//chcial zebysmy mieli zapisane te rzeczy wylosowane w historii
            greetingHistory.add(greeting)
            giftHistory.add(gift)
            updateHistory()
        }
    }
    private fun updateHistory() {
        val history = StringBuilder()
        history.append("History:\n")
        for (i in greetingHistory.indices) {
            history.append("Greeting: ${greetingHistory[i]}, Gift:${giftHistory.getOrNull(i) ?: "Factory Fails, sorry no gift"}\n")
        }
        val historyTextView = findViewById<TextView>(R.id.historyTextView)
        historyTextView.text = history.toString()
    }
    private fun disableAllButtons() {
        val mainLayout = findViewById<GridLayout>(R.id.main)
        for (i in 0 until mainLayout.childCount) {
            val button = mainLayout.getChildAt(i) as Button
            button.isEnabled = false
        }
    }
    private fun showGrinchSurprise() {
        resultTextView.text = SantasElf.getGrinchSurprise()
        resultTextView.setTextColor(Color.RED)
        resultTextView.setBackgroundColor(Color.parseColor("#006400"))
    }
}
