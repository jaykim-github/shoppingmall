package zerobase.shoppingmall.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.payment.dto.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
