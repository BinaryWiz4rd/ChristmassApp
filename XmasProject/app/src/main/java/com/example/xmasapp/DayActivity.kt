package com.example.xmasapp
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        val dayNumber = intent.getIntExtra("DAY_NUMBER", 1)
        val dayTextView = findViewById<TextView>(R.id.textViewDay)
        dayTextView.text = "Gift for Day $dayNumber 🎁"
    }
}