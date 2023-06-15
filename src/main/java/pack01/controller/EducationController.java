package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pack01.domain.Education;
import pack01.domain.type.EducationType;
import pack01.service.EducationService;

import java.util.List;

@Controller
@RequestMapping("/education")
public class EducationController {
    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/save")
    public String save() {
        return "TigerView";
    }

    @PostMapping("/save")
    public String save(Education education) {
        Long id = educationService.save(education);
        System.out.println(id);
        return "TigerView";
    }

    @GetMapping("/alllist")
    public String findAll() {
        List<Education> list = educationService.findAll();
        System.out.println(list);
        return "TigerView";
    }
}
