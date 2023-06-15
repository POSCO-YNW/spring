package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.SocialAccount;
import pack01.repository.ResumeItemRepository;
import pack01.repository.SocialAccountRepository;

import java.util.List;

@Service
public class SocialAccountService {

    private SocialAccountRepository socialAccountRepository;

    @Autowired
    public SocialAccountService(SocialAccountRepository socialAccountRepository){
        this.socialAccountRepository = socialAccountRepository;
    }

    public Long save(SocialAccount socialAccount) {
        return socialAccountRepository.save(socialAccount);
    }

    public SocialAccount findById(Long socialAccountId) {
        return socialAccountRepository.findById(socialAccountId);
    }

    public List<SocialAccount> findAll() {
        return  socialAccountRepository.findAll();
    }

    public void update(SocialAccount socialAccount) {
        socialAccountRepository.update(socialAccount);
    }

    public void delete(Long socialAccountId) {
        socialAccountRepository.delete(socialAccountId);
    }

}
