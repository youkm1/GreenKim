package com.example.greenkim

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DetailPostActivity : AppCompatActivity() {

    private lateinit var commentEditText: EditText
    private lateinit var backButton: ImageButton
    private lateinit var sendButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        commentEditText = findViewById(R.id.commentEditText)
        backButton = findViewById(R.id.back_button)
        sendButton = findViewById(R.id.send_button)

        backButton.setOnClickListener {
            onBackPressed()
        }

        sendButton.setOnClickListener {
            val commentText = commentEditText.text.toString()
            sendCommentToServer(commentText)

        }
    }

    private fun sendCommentToServer(comment: String) {
        // TODO: 댓글을 서버에 전송하는 코드를 작성

        // 댓글을 서버에 전송한 후, 입력 창을 비우고 키보드를 숨깁니다.
        commentEditText.text.clear()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(commentEditText.windowToken, 0)
    }
}