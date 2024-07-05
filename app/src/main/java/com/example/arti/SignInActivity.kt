package com.example.arti

import android.app.Activity
import android.app.Person
import android.content.Intent
import android.net.Uri
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
import com.example.article.User
import com.example.article.UserManager

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        // 로그인 시 입력하는 ID, PW
        val enteredId = findViewById<EditText>(R.id.et_signInId)
        val enteredPw = findViewById<EditText>(R.id.et_signInPw)

        // 로그인버튼, 회원가입 버튼
        val btn_loginSignIn = findViewById<Button>(R.id.btn_loginSignin)
        val btn_loginSignUp = findViewById<Button>(R.id.btn_loginSignup)

        var user1ID = intent.getStringExtra("userid")
        var user1Password = UserManager.findUser(user1ID.toString())?.password

        enteredId.setText(user1ID)
        enteredPw.setText(user1Password)

        // ID 정보가 DB에 없거나, PW가 틀렸거나, 빈칸이 있으면 오류 토스트
        var loginOK1 = false
        var loginOK2 = false

        btn_loginSignUp.setOnClickListener {
            val intent00 = Intent(this, SignUpActivity::class.java)
            startActivity(intent00)
        }

        btn_loginSignIn.setOnClickListener {
            if (enteredId.text.isEmpty() == true || enteredPw.text.isEmpty() == true) {
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else loginOK1 = true

            var matchingId = UserManager.findUser(enteredId.text.toString())

            if (matchingId == null) {
                Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show()
            } else {
                if (matchingId.password != enteredPw.text.toString()) {
                    Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    loginOK2 = true
                }
            }

            if (loginOK1 == true && loginOK2 == true) {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val intent55 = Intent(this, MainViewActivity::class.java)
                intent55.putExtra("user1Id", user1ID.toString())
                startActivity(intent55)
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
                }
    }
}

