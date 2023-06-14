package pack01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Tiger {
    @RequestMapping("/t1")
    public String func01() {
        System.out.println("func01 call");
        return "TigerView"; //view로 가려고 시도합니다.
    }
}
