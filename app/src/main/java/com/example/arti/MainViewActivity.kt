package com.example.arti

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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

        //(경고!!: 앱 실행 후 상단 스크롤 버튼 누르지 마세요 ㅠㅠ.. 궁금해도 누르지마세요 두번 시도 했는데 두번 다 컴터 꺼졌어요..)
        //(경고!!: 앱 실행 후 상단 스크롤 버튼 누르지 마세요 ㅠㅠ.. 궁금해도 누르지마세요 두번 시도 했는데 두번 다 컴터 꺼졌어요..)

        // 상단 가로 스크롤 버튼
        val topImageButton = arrayOf(
            findViewById<ImageButton>(R.id.horizontalScrollViewButton1),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton2),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton3),
            findViewById<ImageButton>(R.id.horizontalScrollViewButton4)
        )

        // 다른 인터넷 사이트로 연결 (문제: 인터넷 연결은 되서 구글까지 뜨는데, 앱 실행하다가 컴퓨터가 꺼져요.)
        //(경고!!: 앱 실행 후 상단 스크롤 버튼 누르지 마세요 ㅠㅠ.. 궁금해도 누르지마세요 두번 시도 했는데 두번 다 컴터 꺼졌어요..)

        fun openWebPage(url:String){
            val uriIntent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(uriIntent)
        }

        val urls = arrayOf(
            "https://www.unicornfactory.co.kr/", //유니콘팩토리
            "https://maily.so/unsexybusinesskr", //언섹시비즈니스
            "https://oneoneone.kr/homefeed", //일일일
            "https://newneek.co/" //뉴닉
        )

        for ((index, button) in topImageButton.withIndex()){
            button.setOnClickListener {
                if(index < urls.size){
                    openWebPage(urls[index])
                }
            }
        }

//
//        // 마이 페이지 버튼
//        val myPageButton = findViewById<ImageButton>(R.id.myPageButton)
//        myPageButton.setOnClickListener {
//            val myIntent1 = Intent(this, myPageActivity::class.java)
//            resultLauncher.launch(myIntent1)
//        }
//
//        // 하단 세로 스크롤 버튼
//        val bottomImageButton = arrayOf(
//            findViewById<ImageButton>(R.id.imageButton1),
//            findViewById<ImageButton>(R.id.imageButton2),
//            findViewById<ImageButton>(R.id.imageButton3),
//            findViewById<ImageButton>(R.id.imageButton4),
//            findViewById<ImageButton>(R.id.imageButton5)
//        )
//
//        // 다른 상세페이지 동일하게 하나만 연결할게요~
//        for(button in bottomImageButton){
//            button.setOnClickListener {
//                val myIntent2 = Intent(this, 다른상세페이지::class.java)
//                resultLauncher.launch(myIntent2)
//            }
//        }

    }
}