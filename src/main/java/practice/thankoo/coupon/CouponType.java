package practice.thankoo.coupon;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum CouponType {

    COFFEE("coffee"),
    MEAL("meal");

    private final String value;

    CouponType(final String value) {
        this.value = value;
    }

    public static CouponType of(final String name) {
        return Arrays.stream(values())
                .filter(it -> it.getValue().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("존재하지 않는 쿠폰 타입입니다."));
    }
}
