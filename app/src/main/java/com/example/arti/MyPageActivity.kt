package com.example.arti

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.article.UserManager

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_page)

        // 회원탈퇴 팝업 함수 - Dialog 활용
        fun showPopup(){
            val inflater = LayoutInflater.from(this)
            val popupView = inflater.inflate(R.layout.mypage_withdrawl_check, null)
            val textview :TextView = popupView.findViewById(R.id.withdrawl_popup)

            val alertDialog = AlertDialog.Builder(this)

                .setPositiveButton("탈퇴") {dialog, which ->
                    Toast.makeText(applicationContext, "탈퇴되었습니다.", Toast.LENGTH_SHORT)
                }
                .setNeutralButton("취소", null)
                .create()

            alertDialog.setView(popupView)
            alertDialog.show()
        }

        // 마이페이지 항목별 변수 생성
        var myPageId = findViewById<EditText>(R.id.et_mypage_id)
        var myPageName = findViewById<EditText>(R.id.et_mypage_name)
        var myPageStartupField = findViewById<EditText>(R.id.et_myPageStartupField)
        var myPagePassword = findViewById<EditText>(R.id.et_myPagePassword)
        var myPagePasswordCheck = findViewById<EditText>(R.id.et_myPagePasswordCheck)

        // 수정 누르기 전에는 EditText 안되게 isEnabled로 비활성화
        myPageId.isEnabled = false
        myPageName.isEnabled = false
        myPageStartupField.isEnabled = false
        myPagePassword.isEnabled = false
        myPagePasswordCheck.isEnabled = false

        // 저장 버튼은 invisible로
        val btn_myPageEdit = findViewById<Button>(R.id.btn_myPageEdit)
        val btn_myPageSave = findViewById<Button>(R.id.btn_myPageSave)
        btn_myPageSave.isInvisible = true

        val btn_withdrawl = findViewById<Button>(R.id.btn_withdrawl)
        val btn_back = findViewById<ImageView>(R.id.iv_backButton)

        // SignInActivity에서 유저 정보 끌어오기
        var userId = intent.getStringExtra("user1Id") // 겟엑스트라 정보 끌어옴
        var myUser = UserManager.findUser(userId.toString()) // 그걸로 유저 찾아냄.

//        Toast.makeText(this, "테스트. 아이디 : ${userId}, 비번 : ${com.example.article.UserManager.findUser(userId.toString())?.password} ", Toast.LENGTH_SHORT).show()
//        테스트 통과


        // 처음 마이페이지 들어갔을 때(수정 전) 항목별 값은 DB에서 끌어온 값으로 표시
        myPageId.setText(myUser?.Id.toString())
        myPageName.setText(myUser?.nickname.toString())
        myPageStartupField.setText(myUser?.startupField.toString())
        myPagePassword.setText("")
        myPagePasswordCheck.setText("")

        // 수정 버튼 클릭하면 ->
        btn_myPageEdit.setOnClickListener{
            btn_myPageEdit.isInvisible = true
            btn_myPageSave.isInvisible = false

            myPageId.isEnabled = true
            myPageName.isEnabled = true
            myPageStartupField.isEnabled = true
            myPagePassword.isEnabled = true
            myPagePasswordCheck.isEnabled = true

            btn_myPageSave.setOnClickListener{

                if (myPageName.text.isEmpty() == true || myPageStartupField.text.isEmpty() == true || myPagePassword.text.isEmpty() == true || myPagePasswordCheck.text.isEmpty()) {
                    Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                }

                if (myPagePassword != myPagePasswordCheck) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    myUser?.Id = myPageId.text.toString()
                    myUser?.password = myPagePassword.text.toString()
                    myUser?.nickname = myPageName.text.toString()
                    myUser?.startupField = myPageStartupField.text.toString()

                    Toast.makeText(this, "수정사항이 저장되었습니다.", Toast.LENGTH_SHORT).show()

                    btn_myPageEdit.isInvisible = false
                    btn_myPageSave.isInvisible = true

                    myPageId.isEnabled = false
                    myPageName.isEnabled = false
                    myPageStartupField.isEnabled = false
                    myPagePassword.isEnabled = false
                    myPagePasswordCheck.isEnabled = false
                }
            }
        }

        btn_withdrawl.setOnClickListener{
            val withdrawlCheckPopup = findViewById<ConstraintLayout>(R.id.withdrawl_popup)
            withdrawlCheckPopup.setOnClickListener{
                showPopup()
            }
        }

        btn_back.setOnClickListener{
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

