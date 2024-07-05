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
        var user1PW = intent.getStringExtra("userpw")
        var user1Name = intent.getStringExtra("user_name")
        var user1StartupField = intent.getStringExtra("user_startupField")
        var existingID = intent.getStringArrayExtra("existingID")
        var existingUsername = intent.getStringArrayExtra("existingUsername")
//        var user1 = intent.getParce(user1)
//        var userList = intent.getParcelableArrayListExtra<User>("userList")

        // ID 정보가 DB에 없거나, PW가 틀렸거나, 빈칸이 있으면 오류 토스트
        var loginOK1 = false
        var loginOK2 = false
        var loginOK3 = false

        btn_loginSignIn.setOnClickListener{
            if (existingID?.contains(enteredId.text.toString()) != false){
                Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show()
            } else loginOK1 = true
            }

            if (enteredId.text.toString() == user1ID) {
                if (enteredPw.text.toString() != user1PW) {
                    Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                } else loginOK2 = true
            }

            if (enteredId.text.isEmpty() == true || enteredPw.text.isEmpty() == true){
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else loginOK3 = true

            if (loginOK1 == true && loginOK2 == true && loginOK3 == true) {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainViewActivity::class.java)
                intent.putExtra("user1Id", user1ID.toString())
                intent.putExtra("user1Pw", user1PW.toString())
                intent.putExtra("user1Name", user1Name.toString())
                intent.putExtra("user1StartupField", user1StartupField.toString())
                startActivity(intent)

                val intent2 = Intent(this, MyPageActivity::class.java)
                intent2.putExtra("user1Id", user1ID.toString())
                intent2.putExtra("user1Pw", user1PW.toString())
                intent2.putExtra("user1Name", user1Name.toString())
                intent2.putExtra("user1StartupField", user1StartupField.toString())
                startActivity(intent2)
            }

//            btn_loginSignIn.setOnClickListener{
//                val intent2 = Intent(this, MyPageActivity::class.java)
//                intent2.putExtra("user1Id", user1ID.toString())
//                intent2.putExtra("user1Pw", user1PW.toString())
//                intent2.putExtra("user1Name", user1Name.toString())
//                intent2.putExtra("user1StartupField", user1StartupField.toString())
//            }

        // 회원가입에서 넘어오면 알아서 ID, PW 입력
        lateinit var resultLauncher: ActivityResultLauncher<Intent>

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val id = result.data?.getStringExtra("userid") ?: ""
                    val pw = result.data?.getStringExtra("userpw") ?: ""
                    enteredId.setText(id)
                    enteredPw.setText(pw)
                }
            }

        btn_loginSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            resultLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        }

    }