package com.aliware.tianchi;

import enums.ServerNameEnum;
import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import randomByWeight.RandomUtil;
import server.ServerMessage;

import java.util.*;

/**
 * @author daofeng.xjf
 * <p>
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {

    public static Map<String, Integer> maxThreads = new HashMap<>();

    private static volatile boolean isSetServermaxThreads = false;

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {

        //设置每台机器的最大线程数，此操作只执行一次
//        if (!isSetServermaxThreads) {
//            for (Invoker invoker : invokers) {
//                maxThreads.put(ServerNameEnum.getByCode(invoker.getUrl().getHost()).name(),
//                        invoker.getUrl().getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS));
//            }
//            isSetServermaxThreads = true;
//        }
        Map<String, Long> available = new HashMap<>();

        //获取每台机器的当前可用线程数
        for (Invoker invoker : invokers) {
            ServerNameEnum serverNameEnum = ServerNameEnum.getByCode(invoker.getUrl().getHost());
//
//            long avail = TestClientFilter.serverMap.get(serverNameEnum.name()).getTotalReceiveReq().get() -
//                    TestClientFilter.serverMap.get(serverNameEnum.name()).getTotalHandleReq().get();
//
//            long currentAvailableThread = maxThreads.get(ServerNameEnum.getByCode(invoker.getUrl().getHost()).name()) - avail;

            long currentAvailableThread=CallbackListenerImpl.serverMap.get(serverNameEnum.name()).getAvail();
            available.put(serverNameEnum.name(),currentAvailableThread);

        }

        //根据各台机器的当前可用线程数，作为权重计算，找到目标机器的名称
        String name = RandomUtil.randByWeight(available);

        //找到目标机器
        Optional<Invoker<T>> target = invokers.stream()
                .filter(invoker -> {
                    // 调整主机名格式一致
                    String hostName = invoker.getUrl().getHost();
                    // 转换成编码并与负载均衡结果比对
                    return name == ServerNameEnum.getByCode(invoker.getUrl().getHost()).name();
                })
                .findFirst();
        if (!target.isPresent()) {
            throw new RpcException("目标服务器不存在");
        }
        return target.get();
    }

}