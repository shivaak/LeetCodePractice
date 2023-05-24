package Atlassian.lld.ratelimiter.tokenbucket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TokenBucketRateLimiterTest {

    /*
      user1 -> 3 req every 5 seconds
      1st 5 seconds -> 1 req , so credit = 2
      //2nd windows
      user1 -> 3 req every 5 seconds
      2nd 5 seconds -> 1 req , so credit = 2
      user1 -> 3 req every 5 seconds
      2nd 5 seconds -> 1 req , so credit = 2
     */

    TokenBucketRateLimiter rateLimiter;
    int userId;

    @BeforeEach
    public void setup(){
        rateLimiter = new TokenBucketRateLimiter(3, Duration.ofSeconds(5), 3);
        userId = 1;
    }

    @Test
    public void testRateLimitAllowRequest(){
        assertTrue(rateLimiter.rateLimit(userId));
    }

    @Test
    public void testRateLimitAllowMultipleRequest(){
        for(int i=1;i<=3;i++){
            assertTrue(rateLimiter.rateLimit(userId));
        }
    }

    @Test
    public void testRateLimitDenyMultipleRequest(){
        for(int i=1;i<=6;i++){
            if(i<=3){
                assertTrue(rateLimiter.rateLimit(userId));
            }else{
                assertFalse(rateLimiter.rateLimit(userId));
            }
        }
    }

    @Test
    public void testRateLimitRefillAfterInterval() throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            if (i <= 3) {
                assertTrue(rateLimiter.rateLimit(userId));
            } else {
                assertFalse(rateLimiter.rateLimit(userId));
            }
        }

        //Sleep for 5 seconds
        Thread.sleep(5000);

        for (int i = 1; i <= 4; i++) {
            if (i <= 3) {
                assertTrue(rateLimiter.rateLimit(userId));
            } else {
                assertFalse(rateLimiter.rateLimit(userId));
            }
        }
    }

    @Test
    public void testRateLimitMultipleUsers()  {
        int user1 = 1;
        int user2 = 2;
        for(int i=1;i<=6;i++){
            if(i<=3){
                assertTrue(rateLimiter.rateLimit(user1));
                assertTrue(rateLimiter.rateLimit(user2));
            }else{
                assertFalse(rateLimiter.rateLimit(user1));
                assertFalse(rateLimiter.rateLimit(user2));
            }
        }
    }

    @Test
    public void testRateLimitCreditTokens() throws InterruptedException {
        assertTrue(rateLimiter.rateLimit(userId));
        Thread.sleep(5000);
        for (int i = 1; i <= 6; i++) {
            if(i<=5) {
                assertTrue(rateLimiter.rateLimit(userId));
            }else
                assertFalse(rateLimiter.rateLimit(userId));
        }
    }

    @Test
    public void testRateLimitCreditTokensScenario2() throws InterruptedException {
        assertTrue(rateLimiter.rateLimit(userId));
        assertTrue(rateLimiter.rateLimit(userId));
        Thread.sleep(5000);
        for (int i = 1; i <= 6; i++) {
            if(i<=4) {
                assertTrue(rateLimiter.rateLimit(userId));
            }else
                assertFalse(rateLimiter.rateLimit(userId));
        }

        Thread.sleep(5000);
        for (int i = 1; i <= 6; i++) {
            if(i<=3) {
                assertTrue(rateLimiter.rateLimit(userId));
            }else
                assertFalse(rateLimiter.rateLimit(userId));
        }
    }
}