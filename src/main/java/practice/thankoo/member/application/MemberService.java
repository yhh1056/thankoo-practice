package practice.thankoo.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.thankoo.member.domain.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;

    public void delete(final Long memberId) {
        publisher.publishEvent(new MemberDeleteEvent(this, memberId));
        memberRepository.deleteById(memberId);
    }
}
