package codesquad.promotion.web;

import codesquad.RestResponse;
import codesquad.promotion.domain.Promotion;
import codesquad.promotion.service.PromotionService;
import codesquad.user.auth.AdminUser;
import codesquad.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/promotions")
public class ApiPromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<RestResponse<Promotion>> create(@AdminUser User user, @RequestBody PromotionDto dto) {
        Promotion promotion = promotionService.create(dto.toEntity());
        return ResponseEntity
                .created(URI.create("/api/promotions/" + promotion.getId()))
                .body(RestResponse.success(promotion));
    }

    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@AdminUser User user, @PathVariable Long id) {
        promotionService.delete(id);
        return RestResponse.success();
    }

}
