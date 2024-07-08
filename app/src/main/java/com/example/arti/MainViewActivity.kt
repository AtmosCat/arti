package com.example.arti

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.UserManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainViewActivity : AppCompatActivity() {

    private  lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 상단 가로 스크롤 버튼
        val topImageButton = arrayOf(
            findViewById<ImageButton>(R.id.horizontalScrollViewButton1),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton2),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton3),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton4)
        )

        fun openWebPage(url:String){
            val uriIntent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(uriIntent)
        }

        val urls = arrayOf(
            "https://newneek.co/", //뉴닉
            "https://oneoneone.kr/homefeed", //일일일
            "https://www.unicornfactory.co.kr/", //유니콘팩토리
            "https://maily.so/unsexybusinesskr" //언섹시비즈니스
        )

        for ((index, button) in topImageButton.withIndex()){
            button.setOnClickListener {
                if(index < urls.size){
                    openWebPage(urls[index])
                }
            }
        }
        // Toast.makeText(this, "테스트. 아이디 : ${user1Id}, 비번 : ${com.example.article.UserManager.findUser(user1Id.toString())?.password} ", Toast.LENGTH_SHORT).show()
        // 지금까지는 계정 확인 잘 됩니다

        // 마이 페이지 버튼
        val user1Id = intent.getStringExtra("user1Id") // 인텐트에서 아이디 받아옵니다

        val myPageButton = findViewById<ImageButton>(R.id.myPageButton)
        myPageButton.setOnClickListener {
            val myIntent123 = Intent(this, MyPageActivity::class.java)
            myIntent123.putExtra("user1Id", user1Id) // 엑스트라에 아이디 담아서 넘겨요
//            resultLauncher.launch(myIntent1)
            startActivity(myIntent123)
            overridePendingTransition(R.transition.slide_in_right,R.transition.slide_out_left) // 화면 전환
        }

        // 하단 세로 스크롤 버튼
        val bottomImageButton = arrayOf(
            findViewById<ImageView>(R.id.imageButton1),
            findViewById<ImageView>(R.id.imageButton2),
            findViewById<ImageView>(R.id.imageButton3),
            findViewById<ImageView>(R.id.imageButton4),
            findViewById<ImageView>(R.id.imageButton5)
        )

        val editorContents = arrayOf(
            findViewById<TextView>(R.id.text1),
            findViewById<TextView>(R.id.text3),
            findViewById<TextView>(R.id.text5),
            findViewById<TextView>(R.id.text7),
            findViewById<TextView>(R.id.text9),
        )

        val titleContents = arrayOf(
            findViewById<TextView>(R.id.text2),
            findViewById<TextView>(R.id.text4),
            findViewById<TextView>(R.id.text6),
            findViewById<TextView>(R.id.text8),
            findViewById<TextView>(R.id.text10),
        )

        for((index, button) in bottomImageButton.withIndex()){
            button.setOnClickListener {
                val intentContents = Intent(this, ContentsActivity::class.java)
                intentContents.putExtra("index", index) // index 값만 EXTRA로 넘겨주면 어떤 글인지 식별가능
                startActivity(intentContents)
                overridePendingTransition(R.transition.slide_in_right,R.transition.slide_out_left) // 화면 전환
            }
            ContentsManager.createDefaultContents()
            bottomImageButton[index].setImageResource(ContentsManager.myContents[index].titleImage)

            editorContents[index].setText("${ContentsManager.myContents[index]._editor}의 추천아티클 ")
            titleContents[index].setText(ContentsManager.myContents[index]._title)
        }
    }
}