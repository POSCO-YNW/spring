package pack01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pack01.domain.Education;
import pack01.domain.type.EducationType;
import pack01.service.EducationService;

@Controller
public class Tiger {
    private final EducationService educationService;

    @Autowired
    public Tiger(EducationService educationService) {
        this.educationService = educationService;
    }


    @RequestMapping("/t1")
    public String func01() {
        System.out.println("func01 call");
        return "TigerView"; //view로 가려고 시도합니다.
    }

    @RequestMapping("/t2")
    public String func02() {
        System.out.println("func01 call");
        educationService.save(new Education(EducationType.GRADUATESCHOOL, "rew", 2, null));
        return "TigerView"; //view로 가려고 시도합니다.
    }
}
