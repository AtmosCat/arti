package com.example.arti

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class ContentsActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contents)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.anywhere_touch)) { v, insets ->
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
            btn_detail.isVisible = false
        }
        //더보기 버튼 -> 클릭하면 :: 전체내용을 보여주고, 버튼을 숨깁니다

        val layout_bottom = findViewById<RelativeLayout>(R.id.layout_bottom)
        layout_bottom.isVisible = true  // 하단버튼 on/off

        val anywhere_touch = findViewById<LinearLayout>(R.id.linear_layout)
        anywhere_touch.setOnTouchListener { v, event -> // "리니어 레이아웃"에 대한 터치리스너
            if (btn_detail.isVisible == false && event.action == MotionEvent.ACTION_UP) {
                    layout_bottom.isVisible = !layout_bottom.isVisible
            }
            true
        }
        // [ 터치리스너 - 하단레이아웃 ON/OFF ]
        /* 손을 떼면, 하단레이아웃 on/off 전환
        불리언을 토글할 때는 a = !a 로 뒤집을 수 있다 */

    }

}
