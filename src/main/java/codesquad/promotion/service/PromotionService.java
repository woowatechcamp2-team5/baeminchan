package codesquad.promotion.service;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion create(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }
}
