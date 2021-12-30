package com.example.mapping.domain.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {
    PREPARING("STATUS_PREPARING","상품 준비 중"),
    Shipping("STATUS_SHIPPING", "배송 중"),
    COMPLETE("STATUS_COMPLETE", "배송 완료");
    private final String key;
    private final String title;
}
