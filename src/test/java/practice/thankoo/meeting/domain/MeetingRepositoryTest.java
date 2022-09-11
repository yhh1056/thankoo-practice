package practice.thankoo.meeting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static practice.thankoo.meeting.domain.MeetingStatus.ON_PROGRESS;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.thankoo.coupon.domain.Coupon;
import practice.thankoo.coupon.domain.CouponContent;
import practice.thankoo.coupon.domain.CouponRepository;
import practice.thankoo.coupon.domain.CouponStatus;
import practice.thankoo.coupon.domain.CouponType;
import practice.thankoo.member.domain.Member;
import practice.thankoo.member.domain.MemberRepository;

@DataJpaTest
class MeetingRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    EntityManager entityManager;

    /**
     * 미팅을 삭제해도 회원은 삭제되지 않는다.
     */
    @Test
    void deleteMeeting() {
        Member sender = memberRepository.save(Member.builder().name("호호").build());
        Member receiver = memberRepository.save(Member.builder().name("후니").build());
        Coupon coupon = 쿠폰_저장(sender, receiver);
        Meeting meeting = 미팅_저장(sender, receiver, coupon);

        meetingRepository.delete(meeting);

        assertThat(memberRepository.findAll()).hasSize(2);
    }

    private Coupon 쿠폰_저장(final Member sender, final Member receiver) {
        CouponContent couponContent = CouponContent.builder()
                .couponType(CouponType.COFFEE)
                .message("곧 만나시죠.")
                .title("호호의 커피 쿠폰")
                .build();

        return couponRepository.save(Coupon.builder()
                .couponContent(couponContent)
                .couponStatus(CouponStatus.RESERVED)
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .build());
    }

    private Meeting 미팅_저장(final Member sender, final Member receiver, final Coupon coupon) {
        Meeting meeting = Meeting.builder()
                .coupon(coupon)
                .members(List.of(sender, receiver))
                .meetingStatus(ON_PROGRESS)
                .meetingTime(LocalDate.now())
                .build();
        return meetingRepository.save(meeting);
    }
}