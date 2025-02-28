package kitchenpos.products.tobe.domain;

import java.util.UUID;

/**
 * 상품 바운디드 컨텍스트에서 메뉴 바운디드 컨텍스트로의 통합을 위한 인터페이스
 * ACL(Anti-Corruption Layer)의 일부로, 의존성 역전 원칙(DIP)을 적용
 */
public interface MenuDisplayStatusUpdater {

    void updateMenusDisplayStatusByProductId(UUID productId);
} 
