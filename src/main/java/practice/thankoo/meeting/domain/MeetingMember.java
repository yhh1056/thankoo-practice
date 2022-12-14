package practice.thankoo.meeting.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.thankoo.member.domain.Member;

@Entity
@Table(name = "meeting_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Builder
    public MeetingMember(final Long id, final Member member, final Meeting meeting) {
        this.id = id;
        this.member = member;
        this.meeting = meeting;
    }

    public MeetingMember(final Member member, final Meeting meeting) {
        this(null, member, meeting);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingMember)) {
            return false;
        }
        MeetingMember that = (MeetingMember) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MeetingMember{" +
                "id=" + id +
                ", member=" + member.getId() +
                ", meeting=" + meeting.getId() +
                '}';
    }
}