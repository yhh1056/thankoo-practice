package practice.thankoo.member.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "image_url", length = 2_000)
    private String imageUrl;

    @Builder
    public Member(final Long id, final String name, final String email, final String socialId, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.socialId = socialId;
        this.imageUrl = imageUrl;
    }

    public void updateName(final String name) {
        this.name = name;
    }

    public boolean hasSameId(final List<Long> ids) {
        return ids.stream()
                .anyMatch(this::isSameId);
    }

    public boolean isSameId(final Long id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", socialId='" + socialId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
