package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack01.domain.File;
import pack01.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) { this.fileService = fileService; }
    @PostMapping("/upload")
    public String uploadFile(@RequestBody File file) {
        Long key = fileService.save(file);
        return "/"; // 글 작성하는 거 만들어지면 하기
    }

}
