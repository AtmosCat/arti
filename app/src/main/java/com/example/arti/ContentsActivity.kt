package com.example.arti

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contents)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.anywher_touch)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ContentsManager.createDefaultContents()
        // ContentsClass의 데이터를 가져옵니다
        val index: Int = intent.getIntExtra("index", 0)
        // 인텐트 EXTRA로 index 값을 받아옵니다

        val et_editor = findViewById<TextView>(R.id.et_editor)
        val et_title = findViewById<TextView>(R.id.et_title)
        et_editor.setText("${ContentsManager.myContents[index]._editor}의 추천 아티클")
        et_title.setText(ContentsManager.myContents[index]._title)
        // 타이틀 :: 제목, 에디터 이름

        var contents:String = if ( ContentsManager.myContents[index].contents.length > 250 ) {
            ContentsManager.myContents[index].contents.substring(0, 250) + "..."
        } else {
            ContentsManager.myContents[index].contents
        } // 본문 250자로 끊어주기

        val et_reason = findViewById<TextView>(R.id.et_reason)
        val et_contents = findViewById<TextView>(R.id.et_contents)
        et_reason.setText("\" ${ContentsManager.myContents[index].reason} \"")
        et_contents.setText(contents)
        // 본문 :: 이유, 내용요약

        val img_title = findViewById<ImageView>(R.id.img_title)
        val titleImage = ContentsManager.myContents[index].titleImage
        img_title.setImageResource(titleImage.toInt())
        // 이미지 :: 이미지 할당하기 (* R.drawable.img_000 은 알고보니 Int 값이었음!!)

        val btn_outlink = findViewById<Button>(R.id.btn_outlink)
        val outlink: String = "${ContentsManager.myContents[index].outlink}"
        btn_outlink.setOnClickListener() {
            val intentOutlink = Intent(Intent.ACTION_VIEW, Uri.parse(outlink))
            startActivity(intentOutlink)
        }
        // 외부링크 버튼 :: 링크 할당

        val btn_back = findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener() {
            finish()
        }
        // 뒤로가기 버튼

        val btn_detail = findViewById<TextView>(R.id.btn_detail)
        btn_detail.setOnClickListener(){
            contents = ContentsManager.myContents[index].contents
            et_contents.setText(contents)
            btn_detail.visibility = View.INVISIBLE


        }


        // 화면 아무 곳이나 터치하면 on/off 되게 하고 싶었으나,
//        콘스트레인트 레이아웃 (화면 전체)나
//                android.R.id.content를 <View> 사용해도 안되길래
//        일단 포기!

//        val anywhere_touch = findViewById<ConstraintLayout>(R.id.anywher_touch)
//        anywhere_touch.setOnClickListener() {
//            finish()
//        }
//
//        val anywhere_touch = findViewById<View>(android.R.id.content)
//        anywhere_touch.setOnClickListener() {
//            finish()
//        }

//        anywhere_touch.setOnTouchListener { v, event ->
//            if (event.action == MotionEvent.ACTION_DOWN) {
//             finish()
//            }
//            true
//        }

    }

}


//어떤 기능을 구현해야할까?

/** 기능개발0. 정보를 받아와서 게시글 보여주는 기능
 - ContentsClass의 인스턴스 Id가 몇번 글인지 받아온다.
001번 글이면 001번 인스턴스의 모든 정보를 찾아줘!
 001. 이미지
 001. 제목
 001. 추천인

 001. 추천이유
 001. 본문
 001. 링크

 - 본문을 125자로 줄인다.
 - 더보기 버튼을 visible

 */

/** 기능개발1.빈칸을 클릭하면 하단 레이아웃을 visible
아무데나 클릭하면,
하단 레이아웃 (더보기버튼, 원문링크 버튼, 그라디언트) visible
다시 아무데나 클릭하면 invisible 된다

단, 더보기를 누르기 전에는
invisible 할수 없다
 */


/** 기능개발2. 원문 링크로 이동하는 기능
 - 클릭리스너 {
 암시적 intent -> 인터넷 브라우저 : "인스턴스. 링크 "
 }
 */

/** 기능개발3. 더보기 버튼

 - 클릭 리스너 {
본문 내용을 125자 -> 무제한 풀어주는 기능
더보기 버튼 invisible
 }
 */

/** 기능개발4. 뒤로가기 버튼
- 클릭리스너 {
intent -> main 액티비티
or
Cancel
}
 */




/**
btn_back
뒤로가기 버튼

img_title
타이틀 이미지

et_editor
추천인

et_title
제목 (1-2줄, 25자)

et_reason
추천이유 (1줄, 20자)

et_contents
본문내용
(더보기 전 : 125자)

layout_bottom
하단버튼 가리개

btn_detail
더보기 버튼

btn_outlink
원본 링크 이동하기 버튼
 */

