
package com.bigdata.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomRandomRule extends AbstractLoadBalancerRule {
    public CustomRandomRule() {
    }

    private int total  = 0 ;//被调用的次数
    private int currentCount = 0; //当前谁在提供服务

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); //获取活着的服务
                List<Server> allList = lb.getAllServers(); //获取所有的服务
//                int serverCount = allList.size();
//                if (serverCount == 0) {
//                    return null;
//                }

//                int index = this.chooseRandomInt(serverCount); //生成区间随机数
//                server = (Server)upList.get(index); //从活着的服务，随机获取一个

                if(total < 5){
                     server = upList.get(currentCount);
                    total ++;
                }else {
                    total = 0;
                    currentCount ++;
                    if(currentCount > upList.size()){
                        currentCount = 0;
                    }
                    server = upList.get(currentCount);  //从活着服务获取指定的服务进行操作
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
