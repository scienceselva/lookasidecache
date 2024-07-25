package selva.lookaside_cache.lookasidecache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import selva.lookaside_cache.lookasidecache.model.User;
import selva.lookaside_cache.lookasidecache.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/test-cache")
    public String testCache() {
        // This is just a test endpoint to check caching
        return "Cache test endpoint";
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}

