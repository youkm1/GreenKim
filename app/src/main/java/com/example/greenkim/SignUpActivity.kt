package com.example.greenkim

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newgreenkim.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var userIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var userIdErrorMessage: TextView
    private lateinit var passwordErrorMessage: TextView
    private lateinit var confirmPasswordErrorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 뒤로가기
        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        userIdEditText = findViewById(R.id.user_id_edit_text)
        passwordEditText = findViewById(R.id.user_password_edit_text)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text)
        userIdErrorMessage = findViewById(R.id.signup_id_error_message)
        passwordErrorMessage = findViewById(R.id.signup_pw_error_message)
        confirmPasswordErrorMessage = findViewById(R.id.confirm_password_error_message)

        userIdEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateUserId()
            }
        }

        passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePassword()
            }
        }

        confirmPasswordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateConfirmPassword()
            }
        }

        val signUpSubmitButton = findViewById<Button>(R.id.signup_submit_button)
        signUpSubmitButton.setOnClickListener {
            if (validateUserId() && validatePassword() && validateConfirmPassword()) {
                Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                // 로그인 액티비티로 이동
                moveToLoginActivity()
            } else {
                Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateUserId(): Boolean {
        val userId = userIdEditText.text.toString()
        // 아이디 유효성 검사
        if (userId.length in 6..12) {
            userIdErrorMessage.visibility = View.GONE
            return true
        } else {
            userIdErrorMessage.visibility = View.VISIBLE
            userIdErrorMessage.setTextColor(0xFFBE2E22.toInt())
            return false
        }
    }

    private fun validatePassword(): Boolean {
        val password = passwordEditText.text.toString()
        // 비밀번호 유효성 검사
        if (password.length in 8..12) {
            passwordErrorMessage.visibility = View.GONE
            return true
        } else {
            passwordErrorMessage.visibility = View.VISIBLE
            passwordErrorMessage.setTextColor(0xFFBE2E22.toInt())
            return false
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val confirmPassword = confirmPasswordEditText.text.toString()
        val password = passwordEditText.text.toString()

        // 확인 비밀번호가 비어 있지 않은지 확인
        if (confirmPassword.isNotEmpty()) {
            // 확인 비밀번호가 비밀번호와 일치하는지 확인
            if (confirmPassword == password) {
                confirmPasswordErrorMessage.visibility = View.GONE
                return true
            } else {
                confirmPasswordErrorMessage.visibility = View.VISIBLE
                confirmPasswordErrorMessage.setTextColor(0xFFBE2E22.toInt()) // 색상 코드 추가
                return false
            }
        } else {
            // 확인 비밀번호가 비어 있으면 에러 메시지를 숨김
            confirmPasswordErrorMessage.visibility = View.GONE
            return false
        }
    }

    private fun moveToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}