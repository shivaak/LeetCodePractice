package RateLimiter;

import java.util.concurrent.TimeUnit;

public class TokenBucket {
    long maxBucketSize;
    long currentBucketSize;

    int refillRate;

    long lastRefilledTimeMillis;

    public TokenBucket(long maxBucketSize, int refillRate){
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        this.currentBucketSize = maxBucketSize;
        lastRefilledTimeMillis = System.currentTimeMillis();
    }

    public boolean allowRequest(int token) {
        refill();
        if(currentBucketSize>=token){
            currentBucketSize-=token;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(now-lastRefilledTimeMillis);
        currentBucketSize = Math.max(maxBucketSize, diffInSeconds*refillRate);
    }
}
