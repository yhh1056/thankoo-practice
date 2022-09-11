package practice.thankoo.meeting.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import practice.thankoo.member.application.MemberDeleteEvent;

@Component
@Slf4j
public class MemberDeleteHandler implements ApplicationListener<MemberDeleteEvent> {

    @Override
    public void onApplicationEvent(final MemberDeleteEvent event) {
        Long id = event.getId();
        log.info("memberId : {}", id);
        log.info("미팅 삭제 로직 수행");
        log.info("예약 삭제 로직 수행");
        log.info("쿠폰 삭제 로직 수행");
    }
}
