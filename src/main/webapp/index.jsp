<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>포스코DX 채용</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            /*font-family: Arial, sans-serif;*/
            position: relative;
        }

        .carousel {
            position: relative;
            width: 100%;
            height: 100vh;
            overflow: hidden;
        }

        .carousel-slider {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .carousel-slide {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            animation: fade-in 2s forwards;
        }

        .carousel-slide:first-child {
            animation-delay: 3s;
        }

        @keyframes fade-in {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        .logo {
            position: absolute;
            top: 10px;
            left: 10px;
            width: 150px;
            height: auto;
            z-index: 1;
        }

        .carousel-overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            z-index: 1;
            color: white;
            gap: 10px;
        }

        .carousel-overlay-text {
            color: white;
            font-size: 24px;
            font-weight: bold;
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px 20px;
            border-radius: 20px;
            cursor: pointer;
            margin-top: 50px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="carousel">
    <div class="carousel-slider">
        <div class="carousel-slide"
             style="background-image: url(/resources/static/images/background/pohang_light2.jpg);"></div>
        <div class="carousel-slide"
             style="background-image: url(/resources/static/images/background/pohang_light.jpg);"></div>
    </div>
    <img src="/resources/static/images/poscoDXWhite.png" alt="Logo" class="logo">
    <div class="carousel-overlay">
        <h1>POCRUIT</h1>
        <p>─────────────────────────────────────────────────</p>
        <h3>PoCruit에 오신걸 환영합니다</h3>
        <a href="/postlist?page=0" class="carousel-overlay-text">지원하러 가기</a>
    </div>
</div>

<script>
    // 이미지 로드 후 애니메이션 시작
    window.addEventListener('load', function() {
        let slides = document.getElementsByClassName('carousel-slide');
        let activeSlide = slides[0];

        activeSlide.style.opacity = 1;

        setInterval(function () {
            activeSlide.style.opacity = 0;
            let nextSlide = activeSlide.nextElementSibling || slides[0];
            nextSlide.style.opacity = 1;
            activeSlide = nextSlide;
        }, 3000);
    });
</script>
</body>
</html>
