package com.example.declaregreenkim

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class DeclareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_declare)

        val imageView: ImageView = findViewById(R.id.imageView2)
        imageView.setOnClickListener{showReportReasonDialog()}



    }

    private fun showReportReasonDialog() {
        val reasons = arrayOf("거짓 정보", "상업 광고", "폭력성/음란성","기타")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("신고 사유 선택")
        builder.setItems(reasons) { dialog, which ->
            val selectedReason = reasons[which]

            dialog.dismiss()
        }
        builder.show()
    }
}