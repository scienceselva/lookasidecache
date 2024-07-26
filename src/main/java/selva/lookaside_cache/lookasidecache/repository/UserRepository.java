package selva.lookaside_cache.lookasidecache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selva.lookaside_cache.lookasidecache.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndName(Long id, String name);
}
