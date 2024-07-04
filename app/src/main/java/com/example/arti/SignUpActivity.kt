package com.example.arti

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val userID = findViewById<EditText>(R.id.edit_text_id)
        val userPassword = findViewById<EditText>(R.id.edit_text_PW)
        val userPasswordCallback = findViewById<EditText>(R.id.edit_text_PW_check)
        val username = findViewById<EditText>(R.id.edit_username)


        val intent = Intent(this, MainViewActivity::class.java)
            intent.putExtra("userid",userID.text.toString())
            intent.putExtra("userpw",userPassword.text.toString())
            intent.putExtra("user_name",username.text.toString())


        val idcheck = findViewById<TextView>(R.id.id_check)
        idcheck.setOnClickListener(){
            var existingID = arrayOf("chacha","arti","article")
            if (userID.text.toString() in existingID){
                Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        val signup_button = findViewById<Button>(R.id.signup_button)
        signup_button.setOnClickListener(){
            //아이디 중복확인 버튼
//            var existingID = arrayOf("chacha","arti","article")
//            if (userID.text.toString() in existingID){
//                Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
//                }


            //입력된 비밀번호와 동일한 내용인지 확인하기
            if (userPassword.text.toString() == userPasswordCallback.text.toString()){
                Toast.makeText(this, "비밀번호 확인", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "입력된 비밀번호가 다릅니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            }


            //닉네임 입력하기
            var existingUsername = arrayOf("챠챠싸","아티","아티클")
            if (username.text.toString() in existingUsername)
                Toast.makeText(this, "중복된 닉네임이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "사용가능합니다.", Toast.LENGTH_SHORT).show()
            }

            startActivity(intent)
        }





        //아이디,비밀번호,비밀번호확인,닉네임 작성성해야만 회원가입 버튼이 작동됨




    }
}

