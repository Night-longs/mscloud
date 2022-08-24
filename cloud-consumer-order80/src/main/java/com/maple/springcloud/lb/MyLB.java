package com.maple.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package_Name: com.maple.springcloud.lb
 * @Auth: Maple
 * @Date: 2022/8/22 18:55
 **/
@Component
public class MyLB implements LoadBalancer {
    //原子类

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 判断接口第几次访问
     *
     * @return
     */
    public final int getAndIncrement() {
        int current;
        String a = "current";
        int next = 0;
        do {
            current = atomicInteger.get();
            //防止越界
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next)); //自旋锁
        System.out.println("*****第几次访问，次数next: " + next);
        return next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，
     * 每次服务重启动后rest接口计数从1开始。
     *
     * @param serviceInstanceList
     * @return
     */
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
