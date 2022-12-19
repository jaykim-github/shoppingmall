package zerobase.shoppingmall.admin.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.payment.dto.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
