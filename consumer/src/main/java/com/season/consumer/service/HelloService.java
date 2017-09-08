package com.season.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.season.consumer.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        String body =  restTemplate.getForEntity("http://service1/hello",String.class).getBody();
        return body;
    }

    @HystrixCollapser(batchMethod = "userInfoServiceSyncAll",
            collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value="2000")})
    public Future<User> userInfoServiceSync(Long id){
        /*return new AsyncResult<User>(){
            @Override
            public User invoke(){
                User user =  restTemplate.getForObject("http://service1/userInfo/{1}",User.class, id);
                return user;
            }
        }.get();*/
//        return restTemplate.getForObject("http://service1/userInfo/{1}",User.class, id);
        return null;
    }

    @HystrixCollapser(batchMethod = "userInfoServiceAll",
            collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value="2000")})
    public Future<User> userInfoService(final Long id){
        System.out.println("单个请求");
        return new AsyncResult<User>(){
            @Override
            public User invoke(){
                User user =  restTemplate.getForObject("http://service1/userInfo/{1}",User.class, id);
                return user;
            }
        };
    }

    @HystrixCommand
    public Future<User> userInfoServiceAll(List<Long> ids){
        System.out.println("合并请求");
        return new AsyncResult<User>(){
            @Override
            public User invoke(){
                return restTemplate.getForObject("http://service1/userInfo?ids={1}",User.class, ids);
            }
        };
    }

    @HystrixCommand
    public List<User> userInfoServiceSyncAll(List<Long> ids){
        System.out.println("合并请求");
        /*return (List<User>) new AsyncResult<User>(){
            @Override
            public User invoke(){
                return restTemplate.getForObject("http://service1/userInfo?ids={1}",List.class, ids);
            }
        }.get();*/
        List<User> users =  restTemplate.getForObject("http://service1/userInfo?ids={1}",List.class, StringUtils.join(ids,","));
        return users;
    }

    @HystrixCommand(observableExecutionMode=ObservableExecutionMode.LAZY)
    public Observable<User> userInfoServiceObserv(final Long id){
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try{
                    if(!subscriber.isUnsubscribed()){
                        User user = restTemplate.getForObject("http://service1/userInfo/{1}",User.class, 1L);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    public String helloFallback(Throwable e){
        e.printStackTrace();
        return "error2";
    }
}
