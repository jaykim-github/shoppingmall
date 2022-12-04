package zerobase.shoppingmall.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.user.dto.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAuthKey(String uuid);
}

