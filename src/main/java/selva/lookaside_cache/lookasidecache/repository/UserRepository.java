package selva.lookaside_cache.lookasidecache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selva.lookaside_cache.lookasidecache.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
