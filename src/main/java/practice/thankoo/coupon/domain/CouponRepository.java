package practice.thankoo.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Coupon c WHERE c.receiverId = :memberId OR c.senderId = :memberId")
    void deleteAllByReceiverIdOrSenderId(@Param("memberId") Long memberId);
}
