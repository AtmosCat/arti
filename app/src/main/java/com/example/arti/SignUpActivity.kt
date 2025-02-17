package com.example.arti

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.article.User
import com.example.article.UserManager

class SignUpActivity : AppCompatActivity() {
    private var isUserIDAvailable = false
    private var isPasswordConfirmed = false
    private var isUsernameAvailable = false

    // 유저정보 저장용 유저 리스트 생성
    var existingID = arrayOf("chacha", "arti", "article")
    var existingUsername = arrayOf("차차", "아티", "아티클")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val userID = findViewById<EditText>(R.id.edit_text_id)
        val userPassword = findViewById<EditText>(R.id.edit_text_PW)
        val userPasswordCallback = findViewById<EditText>(R.id.edit_text_PW_check)
        val username = findViewById<EditText>(R.id.edit_username)
        val userStartupField = findViewById<EditText>(R.id.edit_startUpField)

        val btn_signUpBack = findViewById<ImageView>(R.id.iv_signUpBack)

        val idcheck = findViewById<TextView>(R.id.id_check)
        idcheck.setOnClickListener {
            if (userID.text.toString().isBlank() == false) {
                if (userID.text.toString() in existingID) {
                    isUserIDAvailable = false
                    Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
                } else {
                    isUserIDAvailable = true
                    Toast.makeText(this, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                isUserIDAvailable = false
                Toast.makeText(this, "공백은 입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        val passwordcheck = findViewById<TextView>(R.id.pw_check_bt)
        passwordcheck.setOnClickListener {
            if (userPassword.text.toString().isBlank() == false) {
                if (userPassword.text.toString() == userPasswordCallback.text.toString()) {
                    isPasswordConfirmed = true
                    Toast.makeText(this, "비밀번호가 확인됐습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    isPasswordConfirmed = false
                    Toast.makeText(this, "입력된 비밀번호가 다릅니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            } else {
                isPasswordConfirmed = false
                Toast.makeText(this, "공백은 입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        val usernamecheck = findViewById<TextView>(R.id.username_check)
        usernamecheck.setOnClickListener {
            if (username.text.toString().isBlank() == false) {
                if (username.text.toString() in existingUsername) {
                    isUsernameAvailable = false
                    Toast.makeText(this, "중복된 닉네임이 있습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    isUsernameAvailable = true
                    Toast.makeText(this, "사용가능합니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                isUsernameAvailable = false
                Toast.makeText(this, "공백은 입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        val signup_button = findViewById<Button>(R.id.signup_button)
        signup_button.setOnClickListener {
            // 아이디, 비밀번호, 비밀번호 확인, 닉네임, 창업분야 작성 후 회원가입 버튼이 작동되도록 조건 추가
            if (userID.text.isNotEmpty() && userPassword.text.isNotEmpty() && userPasswordCallback.text.isNotEmpty()
                && username.text.isNotEmpty() && userStartupField.text.isNotEmpty()) {

//                // 비밀번호와 비밀번호 확인 일치 여부 확인
//                if (userPassword.text.toString() == userPasswordCallback.text.toString()) {
//                    // 중복 체크 상태 확인
                    if (isUserIDAvailable && isPasswordConfirmed && isUsernameAvailable) {
                        // 최종 컨펌된 유저 정보 저장 at 데이터클래스
                        UserManager.createUser(userID.text.toString(), username.text.toString(), userStartupField.text.toString(),
                            userPassword.text.toString())
                        val intent = Intent(this, SignInActivity::class.java)
                        intent.putExtra("userid", userID.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "아이디 및 닉네임 중복 확인, 비밀번호 확인을 모두 완료해주세요.", Toast.LENGTH_SHORT).show()
                    }
//                } else {
//                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//                }
            } else {
                Toast.makeText(this, "모든 내용을 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_signUpBack.setOnClickListener{
            finish()
        }


    }
}
