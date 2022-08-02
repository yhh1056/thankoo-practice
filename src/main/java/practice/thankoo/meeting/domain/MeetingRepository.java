package practice.thankoo.meeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Long, Meeting> {
}
