package practice.thankoo.coupon;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponContent {

    private static final int MAX_TITLE_LENGTH = 20;
    private static final int MAX_MESSAGE_LENGTH = 100;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "coupon_type", nullable = false, length = 20)
    private CouponType couponType;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Builder
    public CouponContent(final CouponType couponType, final String title, final String message) {
        validateTitleLength(title);
        validateMessageLength(message);
        this.couponType = couponType;
        this.title = title;
        this.message = message;
    }

    public CouponContent(final String type, final String title, final String message) {
        this(CouponType.of(type), title, message);
    }

    private void validateTitleLength(final String title) {
        if (title.isBlank() || title.length() > MAX_TITLE_LENGTH) {
            throw new RuntimeException("제목 글자수 넘겼어요.");
        }
    }

    private void validateMessageLength(final String message) {
        if (message.isBlank() || message.length() > MAX_MESSAGE_LENGTH) {
            throw new RuntimeException("내용 글자수 넘겼어요.");
        }
    }

    @Override
    public String toString() {
        return "CouponContent{" +
                "couponType=" + couponType +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
