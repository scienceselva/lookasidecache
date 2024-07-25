package selva.lookaside_cache.lookasidecache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        return new JCacheCacheManager(cacheManager);
    }

    @Bean
    public CacheManager ehCacheCacheManager() {
        javax.cache.configuration.Configuration<Object, Object> cacheConfig =
                new MutableConfiguration<Object, Object>()
                        .setStoreByValue(false)
                        .setStatisticsEnabled(true);
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        cacheManager.createCache("users", cacheConfig);
        return cacheManager;
    }
}

