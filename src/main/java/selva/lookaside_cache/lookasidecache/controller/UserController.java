package selva.lookaside_cache.lookasidecache.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.web.bind.annotation.*;
import selva.lookaside_cache.lookasidecache.model.User;
import selva.lookaside_cache.lookasidecache.service.UserService;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUserById(
            @PathVariable Long id,
            @RequestParam(required = false) String name
    ) {
        return userService.getUserById(id, name);
    }

    @GetMapping("/test-cache")
    public String testCache() {
        // This is just a test endpoint to check caching
        return "Cache test endpoint";
    }

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/cache/users")
    public List<String> getCacheContents() {
        Cache<Object, Object> cache = cacheManager.getCache("users");

        List<String> cacheContents = new ArrayList<>();
        if (cache != null) {
            cache.forEach(entry -> cacheContents.add("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
        } else {
            cacheContents.add("Cache 'users' is not available.");
        }
        return cacheContents;
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}

