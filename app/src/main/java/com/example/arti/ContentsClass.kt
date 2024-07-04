package com.example.arti

data class ContentsClass(_index :Int, _editor :String, _title :String){
    var index :Int = _index
    var editor :String = _editor
    var title :String = _title
    var reason :String = ""
    var contents :String = ""
    var outlink :String = ""
    var titleImage :Any = ""
    // 식별번호(ex. 001) / 작성자 / 제목 / 추천이유 / 본문내용 / 외부링크주소 / 이미지@drawable 소스
}
object ContentsManager(){

    val myContents = mutableListOf<ContentsClass>()
    // 콘텐츠 클래스로 만들어진 "인스턴스", 즉 "게시글"정보를 담아두는 리스트입니다.
    // ContentsManager.MyContents 와 같이 언급하세요


    fun createContents(_editor :String, _title :String){
        var myIndex :Int = myContents.size + 1
        myContents.add( ContentsClass(myIndex, _editor :String, _title :String) )
    }
    // 글을 생성하는 메소드입니다. 식별번호는 자동으로 생성합니다.

    fun createDefaultContents(){
        // 글 3개 자동생성
        createContents("허민", "아무 주제로나 창업하세요")
        createContents("은택", "창업가는 수염을 길러야 한다")
        createContents("은혜", "스파르타! 들고 일어납시다!")

        myContents[0].reason = "창업을 망설이는 분들께 추천합니다"
        myContents[1].reason = "리더쉽 있는 창업가가 되고 싶다면 필독!"
        myContents[2].reason = "스파르타 수업을 처음 듣는 분께 추천합니다"

        myContents[0].contents = "PMF를 찾으려면 타겟이 뾰족해야 한다 !!! 마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데"
        myContents[1].contents = "PMF를 찾으려면 타겟이 뾰족해야 한다 !!! 마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데"
        myContents[2].contents = "PMF를 찾으려면 타겟이 뾰족해야 한다 !!! 마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데마케팅 자원이 많은 비대기업도 버릴 타겟, 잡을 타겟 뾰족하게 해야 성공하는데"

        myContents[0].outlink = "https://velog.io/@kt8218/%EC%B0%BD%EC%97%85101"
        myContents[1].outlink = "https://velog.io/@kt8218/%EC%B0%BD%EC%97%85101"
        myContents[2].outlink = "https://velog.io/@kt8218/%EC%B0%BD%EC%97%85101"

        myContents[0].titleImage = ""
        myContents[1].titleImage = ""
        myContents[2].titleImage = ""
    }


}