package practice.thankoo.meeting.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingTime {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "time_zone", length = 20, nullable = false)
    private String timeZone;

    @Builder
    public MeetingTime(final LocalDate date, final LocalDateTime time, final String timeZone) {
        validateTime(time);
        validateEqualDate(date, time);
        this.date = date;
        this.time = time;
        this.timeZone = timeZone;
    }

    private void validateTime(final LocalDateTime meetingTime) {
        if (LocalDateTime.now().isAfter(meetingTime)) {
            throw new RuntimeException("잘못된 시간입니다.");
        }
    }

    private void validateEqualDate(final LocalDate meetingDate, final LocalDateTime meetingTime) {
        if (!meetingDate.isEqual(meetingTime.toLocalDate())) {
            throw new RuntimeException("시간과 날짜가 다릅니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingTime)) {
            return false;
        }
        MeetingTime that = (MeetingTime) o;
        return Objects.equals(date, that.date) && Objects.equals(time, that.time)
                && Objects.equals(timeZone, that.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, timeZone);
    }

    @Override
    public String toString() {
        return "MeetingTime{" +
                "date=" + date +
                ", time=" + time +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}