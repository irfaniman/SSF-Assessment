package vttp2022.ssf.SSFAssessment.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class CryptoRepository {
    
    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    // key & value for redis
    public void save(String fysm, String payload) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(fsym.toLowerCase(), payload, Duration.ofMinutes(cacheTime));
    }

    // purpose is to get back what we save
    public Optional<String> get(String city) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(city.toLowerCase());
        if (null == value)
            return Optional.empty(); // empty box
        return Optional.of(value); // box with data
    }
}
