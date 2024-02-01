package com.example.newgreenkim

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import android.provider.MediaStore
import android.widget.LinearLayout

class SettingActivity : AppCompatActivity() {

    private val uploadedImages = mutableListOf<Uri>()

    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    private lateinit var imageView7: ImageView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting);

        imageView7 = findViewById(R.id.imageView7)

        val imageView7 = findViewById<ImageView>(R.id.imageView7)


        imageView7.setOnClickListener {
            showImageSourceDialog()
        }



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

    private fun showImageSourceDialog() {
        val options = arrayOf("갤러리에서 사진 가져오기")

        AlertDialog.Builder(this)
            .setTitle("이미지 업로드")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openGallery()
                }
            }
            .show()
    }

    private fun openGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val selectedImageUri: Uri? = data?.data
                    selectedImageUri?.let {
                        uploadedImages.add(it)
                    }
                }
            }
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