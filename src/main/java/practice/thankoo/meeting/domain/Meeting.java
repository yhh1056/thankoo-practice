package practice.thankoo.meeting.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import practice.thankoo.coupon.Coupon;
import practice.thankoo.member.domain.Member;

@Entity
@Table(name = "meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MeetingMembers meetingMembers;

    @Column(name = "meetingTime", nullable = false)
    private LocalDate meetingTime;

    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Builder
    public Meeting(final Long id,
                   final List<Member> members,
                   final LocalDate meetingTime,
                   final MeetingStatus meetingStatus,
                   final Coupon coupon) {
        this.id = id;
        this.meetingMembers = new MeetingMembers(convertToMeetingMember(members));
        this.meetingTime = meetingTime;
        this.meetingStatus = meetingStatus;
        this.coupon = coupon;
    }

    private List<MeetingMember> convertToMeetingMember(final List<Member> members) {
        return members.stream()
                .map(member -> MeetingMember.builder()
                        .member(member)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", meetingMembers=" + meetingMembers +
                ", meetingTime=" + meetingTime +
                ", meetingStatus=" + meetingStatus +
                ", coupon=" + coupon +
                '}';
    }
}