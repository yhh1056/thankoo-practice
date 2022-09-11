package practice.thankoo.coupon.domain;

import java.util.Arrays;

public enum CouponStatus {

    NOT_USED,
    RESERVING,
    RESERVED,
    USED,
    EXPIRED;

    public static CouponStatus of(final String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("쿠폰 상태 못 찾았어요."));
    }

    public boolean isNotUsed() {
        return this == NOT_USED;
    }

    public boolean isInReserve() {
        return isReserving() || this == RESERVED;
    }

    public boolean isReserving() {
        return this == RESERVING;
    }
}
