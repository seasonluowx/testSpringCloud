package com.season.consumer.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.season.consumer.entity.User;
import rx.Observable;

public class UserObservableCommand extends HystrixObservableCommand<User>{
    protected UserObservableCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    protected UserObservableCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Observable<User> construct() {
        return null;
    }
}
