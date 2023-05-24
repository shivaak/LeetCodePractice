package Atlassian.lld.ratelimiter.tokenbucket;

import Atlassian.lld.ratelimiter.RateLimiter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter implements RateLimiter {

    int bucketCapacity;
    Duration interval;

    int tokensPerInterval;

    Map<Integer, UserBucket> userBucketMap; // Space : O(N)

    public TokenBucketRateLimiter(int bucketCapacity, Duration interval, int tokensPerInterval) {
        this.bucketCapacity = bucketCapacity;
        this.interval = interval;
        this.tokensPerInterval = tokensPerInterval;
        this.userBucketMap = new HashMap<>();
    }

    @Override
    public boolean rateLimit(int customerId) {
        if(!userBucketMap.containsKey(customerId)){ //O(1)
            userBucketMap.put(customerId, new UserBucket(bucketCapacity, interval, tokensPerInterval)); //O(1)
        }
        UserBucket user = userBucketMap.get(customerId); //O(1)
        user.refillToken(); // O(1)
        if(user.getCurrentTokenCount()>0){
            user.reduceCurrentTokenCount();
            return true;
        } else if (user.haveCredits()) {
            user.reduceCreditCount();
            return true;
        }
        return false;
    }
}
