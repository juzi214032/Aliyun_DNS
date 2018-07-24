package com.juzi.Aliyun.dns;

import com.juzi.dom4j.XmlReader;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过域名获取IP
 * Author: Juzi
 * Time: 2018/7/24 18:30
 * Blog: http://juzibiji.top
 */
public class GetIp {
    private static String ip = null;
    private static String inetAddress = XmlReader.getRoot().element("domain").elementText("InetAddress");;
    public static String getIp(){
        try {
            ip = InetAddress.getByName(inetAddress).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
