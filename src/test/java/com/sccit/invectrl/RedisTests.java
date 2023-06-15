package com.sccit.invectrl;

import UTILS.BYTES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = AccumulatorApplication.class)
public class RedisTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testReadTokenAccess() {
        // 测试Redis的Value
        String redisKey = "TokenAccess_whz";
        //redisTemplate.opsForValue().set(redisKey, "hello");
        Object result = redisTemplate.opsForValue().get(redisKey);
        byte[] bytes = (byte[])result;
        String str = UTILS.BYTES.convertString(bytes);
        System.out.println("token is :"+str);
    }

   // @Test
    public void testRedisValue() {
        // 测试Redis的Value
        String redisKey = "test:value";
        redisTemplate.opsForValue().set(redisKey, "hello");
        Object result = redisTemplate.opsForValue().get(redisKey);
        System.out.println(result);
    }

  //  @Test
    public void testRedisTransaction() {
        // 测试Redis事务
        List<Object> txResults = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("test:01", 1);
                operations.opsForValue().set("test:02", 2);
                operations.opsForValue().set("test:03", 3);
                System.out.println(operations.opsForValue().get("test:01")); //读取不到结果
                return operations.exec();
            }
        });
        System.out.println(txResults);
    }
}