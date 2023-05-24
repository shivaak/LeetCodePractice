package Atlassian.lld.ratelimiter.tokenbucket;

import java.time.Duration;
import java.time.Instant;

public class UserBucket {

    int bucketCapacity;
    Duration interval;

    int credit;

    int tokensPerInterval;

    int currentTokenCount;

    Instant lastRefilledTimeStamp;

    public UserBucket(int bucketCapacity, Duration interval, int tokensPerInterval) {
        this.bucketCapacity = bucketCapacity;
        this.interval = interval;
        this.tokensPerInterval = tokensPerInterval;
        this.currentTokenCount = bucketCapacity;
        this.lastRefilledTimeStamp = Instant.now();
        this.credit=0;
    }

    public int getCurrentTokenCount() {
        return currentTokenCount;
    }

    public void reduceCurrentTokenCount() {
        currentTokenCount--;
    }

    public void refillToken() {
        Instant now = Instant.now();
        Duration duration = Duration.between(lastRefilledTimeStamp, now);
        if(duration.getSeconds() >= interval.getSeconds() && currentTokenCount>0){
           credit = currentTokenCount;
        }
        int numberOfTokenToBeFilled = (int) (duration.getSeconds() / interval.getSeconds()) * tokensPerInterval;
        currentTokenCount = Math.min(currentTokenCount + numberOfTokenToBeFilled, bucketCapacity);
        this.lastRefilledTimeStamp = Instant.now();
    }

    public boolean haveCredits() {
        return credit > 0;
    }

    public void reduceCreditCount() {
        credit--;
    }
}
