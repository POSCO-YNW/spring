<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            font-family: Arial, sans-serif;
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
            animation: slide 3s infinite;
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
            animation: slide 3s infinite;
        }

        @keyframes slide {
            0%, 100% {
                opacity: 0;
                transform: scale(1.2);
            }

            10%, 90% {
                opacity: 1;
                transform: scale(1);
            }
        }

        @keyframes fade {
            0%, 100% {
                opacity: 0;
            }

            10%, 90% {
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
        }

        .carousel-overlay-text {
            color: white;
            font-size: 24px;
            font-weight: bold;
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px 20px;
            border-radius: 20px;
            cursor: pointer;
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
    <img src="/resources/static/images/poscoDX.png" alt="Logo" class="logo">
    <div class="carousel-overlay">
        <a href="/postlist" class="carousel-overlay-text">지원하러 가기 &gt;</a>
    </div>
</div>

<script>
    setInterval(function () {
        let slides = document.getElementsByClassName('carousel-slide');
        let activeSlide = document.querySelector('.carousel-slide.active');

        activeSlide.classList.remove('active');
        let nextSlide = activeSlide.nextElementSibling || slides[0];
        nextSlide.classList.add('active');
    }, 3000);
</script>
</body>
</html>
