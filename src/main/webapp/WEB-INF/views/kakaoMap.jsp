<%@ page import="java.util.List" %>
<%@ page import="pack01.domain.Department" %>
<%@ page import="pack01.domain.Post" %>
<%@ page import="pack01.dto.post.response.PostDepartmentResponse" %>
<%@ page import="org.springframework.validation.annotation.Validated" %>
<%@ page import="org.springframework.beans.factory.annotation.Value" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지도로 채용공고 보기</title>
    <style>
        #map {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 9999;
        }
        #button-container {
            position: fixed;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 9999;
        }

        .map-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="map"></div>
<div id="button-container">
    <button class="map-button" onclick="redirectToPostList()">채용공고 리스트로 보기</button>
</div>
<%
    String kakao_api_key = (String) request.getAttribute("kakao_api_key");
    System.out.println(kakao_api_key);
%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=kakao_api_key%>"></script>
<script>
    // 지도 생성
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(37.5665, 126.9780), // 대한민국 중심 좌표
        level: 14 // 지도 확대 레벨 14
    };
    var map = new kakao.maps.Map(container, options);

    //포스코 센터: 37.50579, 127.0561533
    // 포스코 플랜텍: 37.5032713, 127.0466823
    // 포스코타워 역삼: 37.4993385, 127.0337927
    // 포스코DX(서울): 37.5058736, 127.0559661
    // 인터내셔널: 37.3891704, 126.6441128
    // 포스코X(판교): 37.4036479, 127.1032699
    // 포스코 포항: 36.0190178, 129.3434808
    // 포스코 광야ㅇ: 34.9328653, 127.7361051
    // 핑(마커) 추가
    let markers = [];
    let names = [];

    const markerClick = (index)=>{
        window.location.href = "/postlist/post?id=" + index;
    }
    const markerCreate = (x, y)=>{
        markers.push(new kakao.maps.LatLng(x, y));
    }
    <%
        List<PostDepartmentResponse> departments = (List<PostDepartmentResponse>) request.getAttribute("postDepartment");
        for (PostDepartmentResponse dept : departments) {
            String name = dept.getName();
    %>
            names.push("<%=name%>");
            // names.forEach((name)=>{
            //     console.log("이름: "+name);
            // })
            markerCreate(<%=dept.getX()%>, <%=dept.getY()%>);
    <%
    }
    %>


    markers.forEach((markerPosition, index) => {
        let marker = new kakao.maps.Marker({
            position: markerPosition,
            clickable: true
        });
        marker.setMap(map);
        let iwContent = '<div style="padding:5px;">'+names[index]+' 부서 채용</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        // let iwContent = '<div>hello</div>';
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent
        });

        kakao.maps.event.addListener(marker, 'mouseover', function() {
            infowindow.open(map, marker);
        });

        kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });
        kakao.maps.event.addListener(marker, 'click', function() {
            markerClick(index+1);  // 함수 호출이 아닌 콜백 함수를 전달!
        });
    });
    const redirectToPostList = ()=> {
        window.location.href = "/postlist";
    }
</script>

</body>
</html>