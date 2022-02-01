package com.liudong.springcloud.lb;



import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoundLoadBanlancer implements LoadBalancer {

    private AtomicInteger balance = new AtomicInteger(0);

    public int next(){
        while (true){
            int pre = balance.get();
            int next = pre >= Integer.MAX_VALUE ? 0: pre + 1;
            if(balance.compareAndSet(pre, next)){
                System.out.println("第" + next + "次请求");
                return next;
            }
        }
    }

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        int next = next();
        if(instances == null || instances.size() == 0){
            return null;
        }
        return instances.get(next % instances.size());
    }
}
