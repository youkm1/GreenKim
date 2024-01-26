package com.example.newgreenkim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting);

        supportActionBar?.setDisplayHomeAsUpEnabled(true)




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
        val switchView1: SwitchCompat = findViewById(R.id.switchView1)

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView1.text = "댓글 알림"
            } else {
                textView1.text = "댓글 알림"
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



        val testButton3: Button = findViewById<Button>(R.id.button5)
        testButton3.setOnClickListener {
            val nextIntent = Intent(this, NewPWActivity::class.java)
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