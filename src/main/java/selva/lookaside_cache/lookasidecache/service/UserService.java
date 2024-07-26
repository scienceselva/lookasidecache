package selva.lookaside_cache.lookasidecache.service;

import jakarta.annotation.PostConstruct;
import selva.lookaside_cache.lookasidecache.model.User;
import selva.lookaside_cache.lookasidecache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id + ':' + #name")
    public Optional<User> getUserById(Long id, String name) {

        if(name == null) {
            return userRepository.findById(id);
        }else{
            return userRepository.findByIdAndName(id, name);
        }

    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
