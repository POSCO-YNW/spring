package pack01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
    @RequestMapping("/mypage")
    public String myPage(){
        System.out.println("마이페이지");
        return "mypage/mypageView";
    }
}
