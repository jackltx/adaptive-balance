package com.aliware.tianchi;

import enums.ServerNameEnum;
import org.apache.dubbo.rpc.listener.CallbackListener;
import server.ServerMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daofeng.xjf
 *
 * 客户端监听器
 * 可选接口
 * 用户可以基于获取获取服务端的推送信息，与 CallbackService 搭配使用
 *
 */
public class CallbackListenerImpl implements CallbackListener {

    public static Map<String, ServerMessage> serverMap = new HashMap();
    private static final String _PORT20880 = ":20880";
    private static final String _PORT20870 = ":20870";
    private static final String _PORT20890 = ":20890";
    static {
        serverMap.put(ServerNameEnum.SMALL.name(),
                ServerMessage.buildServer().setUrl(ServerNameEnum.SMALL.getCode() + _PORT20880).setHost(ServerNameEnum.SMALL.getCode()));
        serverMap.put(ServerNameEnum.MEDIUM.name(),
                ServerMessage.buildServer().setUrl(ServerNameEnum.MEDIUM.getCode() + _PORT20870).setHost(ServerNameEnum.MEDIUM.getCode()));
        serverMap.put(ServerNameEnum.LARGE.name(),
                ServerMessage.buildServer().setUrl(ServerNameEnum.LARGE.getCode() + _PORT20890).setHost(ServerNameEnum.LARGE.getCode()));
    }

    @Override
    public void receiveServerMsg(String msg) {
        String[] strings = msg.split(" ");
        String serverName=strings[0];
        int avail = Integer.parseInt(strings[1]);
        ServerNameEnum server = ServerNameEnum.getByname(serverName);
        serverMap.get(server.name()).setAvail(avail);

        System.out.println("receive msg from server :" + msg);
    }

}
