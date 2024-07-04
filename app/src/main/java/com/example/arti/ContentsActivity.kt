package com.example.arti

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contents)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


//어떤 기능을 구현해야할까?

/** 기능개발0. 정보를 받아와서 게시글 보여주는 기능
 - ContentsClass의 인스턴스 Id가 몇번 글인지 받아온다.
001번 글이면 001번 인스턴스의 모든 정보를 찾아줘!
 001. 이미지
 001. 제목
 001. 제목번호

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

et_title_num
카테고리, 회차 (1줄)

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

