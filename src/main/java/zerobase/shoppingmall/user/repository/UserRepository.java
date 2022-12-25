package zerobase.shoppingmall.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.shoppingmall.user.dto.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAuthKey(String uuid);


    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
}

