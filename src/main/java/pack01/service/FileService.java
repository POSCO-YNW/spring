package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.File;
import pack01.repository.FileRepository;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) { this.fileRepository = fileRepository; }

    public Long save(File file) { return fileRepository.save(file); }
    public void update(File file) { fileRepository.update(file); }
    public void delete(Long fileId) { fileRepository.delete(fileId); }
    public File findById(Long fileId) { return fileRepository.findById(fileId); }
    public List<File> findByPostId(Long postId) { return fileRepository.findByPostId(postId); }
}
