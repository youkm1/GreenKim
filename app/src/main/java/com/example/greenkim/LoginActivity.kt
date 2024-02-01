package com.example.greenkim

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newgreenkim.R

class LoginActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signinErrorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)
        val signupButton = findViewById<Button>(R.id.signup_button)

        idEditText = findViewById(R.id.id_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        signinErrorMessage = findViewById(R.id.signin_error_message)

        loginButton.setOnClickListener {
            val id = idEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 아이디와 비밀번호가 빈 경우
            if (id.isEmpty()) {
                showErrorMessage("아이디를 입력해주세요.")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                showErrorMessage("비밀번호를 입력해주세요.")
                return@setOnClickListener
            }

            // 로그인 로직 구현 필요
            // 아이디 없음, 비밀번호 불일치

            // 일단 로그인 성공 후 MainActivity로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signupButton.setOnClickListener {
            // SignUpActivity로 이동
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMessage(message: String) {
        signinErrorMessage.text = message
        signinErrorMessage.visibility = View.VISIBLE
    }
}