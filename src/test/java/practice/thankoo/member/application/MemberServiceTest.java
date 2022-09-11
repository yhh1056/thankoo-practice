package practice.thankoo.member.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.thankoo.member.domain.Member;
import practice.thankoo.member.domain.MemberRepository;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 삭제하면 관련된 쿠폰, 예약, 미팅을 삭제한다.")
    void delete() {
        Member sender = memberRepository.save(Member.builder().name("호호").build());

        memberService.delete(sender.getId());
    }
}