package practice.thankoo.meeting.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingMembers {

    private static final int STANDARD_MEMBER_COUNT = 2;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.PERSIST)
    private List<MeetingMember> meetingMembers = new ArrayList<>();

    @Builder
    public MeetingMembers(final List<MeetingMember> meetingMembers) {
        validateMemberCount(meetingMembers);
        this.meetingMembers.addAll(meetingMembers);
    }

    private void validateMemberCount(final List<MeetingMember> meetingMembers) {
        if (meetingMembers.size() != STANDARD_MEMBER_COUNT) {
            throw new RuntimeException("참여자는 2명이어야합니다.");
        }
    }

    @Override
    public String toString() {
        return "MeetingMembers{" +
                "meetingMembers=" + meetingMembers +
                '}';
    }
}
