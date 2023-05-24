package Atlassian.lld.ratelimiter;

/*
/**

Imagine we are building an application that is used by many different customers. We want to avoid one customer being able to overload the system by sending too many requests, so we enforce a per-customer rate limit.

The rate limit is defined as Each customer can make 3 requests in 10 seconds

Perform rate-limiting logic for provided customer ID. Return true if the request is allowed and false if it is not.

 boolean rateLimit(int customerId);
*/

public interface RateLimiter {
    boolean rateLimit(int customerId);
}
