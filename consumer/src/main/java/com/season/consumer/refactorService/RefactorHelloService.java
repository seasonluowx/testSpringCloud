package com.season.consumer.refactorService;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by seasonluowx on 2017/9/10 0010.
 */
@FeignClient(value="server1")
public interface RefactorHelloService extends com.season.service.helloService {
}
