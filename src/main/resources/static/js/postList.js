/**
 * main JS
 * Created by rwkang on 2023.09.25
 */

"use strict";

async function getPostList() {
    // let response = await fetch("http://localhost:8080/users/signin");
    let response = await fetch("http://localhost:8080/posts/list");
    // let response = await fetch("http://192.168.0.8:8080/posts/list");

    console.log("response: " + response);

    // 1.Spring Boot에서 가져온 데이터를 그대로 뿌리기
    // let data = await response.text(); // 일반 문자열 임에 주의 : 보이는 것은 "JSON" 형식 같이 보이지만...
    //document.getElementById("list").innerHTML = data;

    // 2. map()으로 가공
    let data = await response.json(); // 일반 문자열을 "JSON" 형식으로 변환...
    let mapData = data.map(post => '<li>' + post.title + '</li>').join('');
    document.getElementById("list").innerHTML = '<ul>' + mapData + '</ul>';
}

document.getElementById("btn").addEventListener("click", getPostList);






// 조박사 monitor Project main.js : 2023.09.25 현재는 실행 안 됨.
// $(function() {

    // 返回顶部的效果事件
    // NProgress.start();

    // $(Window).scroll(function () { //只要窗口滚动,就触发下面代码
    //     var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
    //     if (scrollt > 200) {
    //         $("#goToTop").fadeIn(400); // fade in, 페이드 인, 淡入
    //     } else {
    //         $("#goToTop").stop().fadeOut(400); // 만약 반환 또는 초과하면 페이드 인. 반드시 stop() 정지 전에 동영상 추가해야 한다. 안 그러면 반짝거림이 출력된다. // 如果返回或者没有超过,就淡入.必须加上stop()停止之前动画,否则会出现闪动
    //     }
    // });
    //
    // $("#goToTop").click(function() { //当点击标签的时候,使用animate在200毫秒的时间内,滚到顶部
    //     $("html, body").animate({scrollTop: "Opx"}, 200);
    // });

    // NProgress.done();

// });