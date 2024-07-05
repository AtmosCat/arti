package com.example.arti

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        // 로그인 시 입력하는 ID, PW
        val userId = findViewById<EditText>(R.id.et_signInId)
        val userPw = findViewById<EditText>(R.id.et_signInPw)

        // ID 정보가 DB에 없거나, ID가 틀렸거나, PW가 틀렸으면 오류 토스트

        // 로그인버튼, 회원가입 버튼
        val btn_loginSignIn = findViewById<Button>(R.id.btn_loginSignin)
        val btn_loginSignUp = findViewById<Button>(R.id.btn_loginSignup)

        btn_loginSignIn.setOnClickListener{
            if (userId.text.isEmpty() == true || userPw.text.isEmpty() == true){
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainViewActivity::class.java)
                intent.putExtra("userId", userId.text.toString())
                intent.putExtra("userPw", userPw.text.toString())
                startActivity(intent)
            }
        }

        lateinit var resultLauncher: ActivityResultLauncher<Intent>

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""
                    userId.setText(id)
                    userPw.setText(pw)
                }
            }

        btn_loginSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
            resultLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}