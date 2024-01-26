package com.example.newgreenkim

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

class SettingActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting);

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(applicationContext, WithdrawActivity::class.java)
        }



        val textView1: TextView = findViewById(R.id.textView1)
        val switchView: SwitchCompat = findViewById(R.id.switchView1)

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView1.text = "댓글 알림"
            } else {
                textView1.text = "댓글 알림"
            }
        }

        val textView2: TextView = findViewById(R.id.textView2)
        val switchView2: SwitchCompat = findViewById(R.id.switchView2)

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView1.text = "좋아요 알림"
            } else {
                textView1.text = "좋아요 알림"
            }
        }



        val testButton: Button = findViewById<Button>(R.id.button2)
        testButton.setOnClickListener {
            val nextIntent = Intent(this, MyPageActivity1::class.java)
            startActivity(nextIntent)
        }

        val testButton1: Button = findViewById<Button>(R.id.button3)
        testButton1.setOnClickListener {
            val nextIntent = Intent(this, MyPageActivity2::class.java)
            startActivity(nextIntent)
        }






        val testButton4: Button = findViewById<Button>(R.id.button7)
        testButton4.setOnClickListener {
            val nextIntent = Intent(this, WithdrawActivity::class.java)
            startActivity(nextIntent)
        }








    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}