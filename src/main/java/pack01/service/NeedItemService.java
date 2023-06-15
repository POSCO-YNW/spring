package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.NeedItem;
import pack01.repository.NeedItemRepository;
import pack01.repository.PostRepository;

import java.util.List;

@Service
public class NeedItemService {
    private final NeedItemRepository needItemRepository;

    @Autowired
    public NeedItemService(NeedItemRepository needItemRepository) {
        this.needItemRepository = needItemRepository;
    }

    public Long save(NeedItem needItem) { return needItemRepository.save(needItem); }
    public void update(NeedItem needItem) { needItemRepository.update(needItem); }
    public void delete(Long needItemId) { needItemRepository.delete(needItemId); }
    public NeedItem findByNeedItemId(Long needItemId) { return needItemRepository.findByNeedItemId(needItemId); }
    public List<NeedItem> findByPostId(Long postId) { return needItemRepository.findByPostId(postId); }
}
