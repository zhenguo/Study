package com.quxiangtech.binder.common_interface;

import android.os.IInterface;

public interface SayHi extends IInterface {
    /**
     * 客户端向服务端发送一条消息，服务端同时返回一条消息给客户端
     *
     * @param message 客户端发送的消息
     * @return 服务端返回给客户端的消息
     */
    String sayHi(String message);
}
