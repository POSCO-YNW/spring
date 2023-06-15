package pack01.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import pack01.domain.Education;
import pack01.domain.User;
import pack01.interceptor.SessionConst;
import pack01.service.EducationService;

@Controller
@RequestMapping("/education")
public class EducationController {
    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/save")
    public String save(@SessionAttribute(name = SessionConst.ROLE, required = false) User loginUser) {
        return "TigerView";
    }

    @PostMapping("/save")
    public String save(@SessionAttribute(name = SessionConst.ROLE, required = false) User loginUser,
                       Education education) {
        Long id = educationService.save(education);
        System.out.println(id);
        return "TigerView";
    }

    @GetMapping("/alllist")
    public String findAll(@SessionAttribute(name = SessionConst.ROLE, required = false) User loginUser) {
        List<Education> list = educationService.findAll();
        System.out.println(list);
        return "TigerView";
    }
}