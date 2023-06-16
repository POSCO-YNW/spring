package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Certification;
import pack01.repository.CertificationRepository;

import java.util.List;

@Service
public class CertificationService {
    private CertificationRepository certificationRepository;

    @Autowired
    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public Long save(Certification certification) {
        return certificationRepository.save(certification);
    }

    public Certification findById(Long certificationId) {
        return certificationRepository.findById(certificationId);
    }

    public List<Certification> findAll() {
        return certificationRepository.findAll();
    }

    public void update(Certification certification) {
        certificationRepository.update(certification);
    }

    public void delete(Long certificationId) {
        certificationRepository.delete(certificationId);
    }

    public List<Certification> findByResumeId(Long resumeId) {
        return certificationRepository.findByResumeId(resumeId);
    }
}
