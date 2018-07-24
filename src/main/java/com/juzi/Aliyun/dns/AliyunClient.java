package com.juzi.Aliyun.dns;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.juzi.dom4j.XmlReader;

/**
 * 该类负责创建一个Client对象，供其他类使用
 * Author: Juzi
 * Time: 2018/7/24 17:19
 * Blog: http://juzibiji.top
 */
public class AliyunClient {
    //定义客户端
    private static IAcsClient client = null;
    //AccessKeyId
    private static String accessKeyId = XmlReader.getRoot().element("account").elementText("AccessKeyId");
    //AccessKeySecret
    private static String accessKeySecret = XmlReader.getRoot().element("account").elementText("AccessKeySecret");
    //regionId----必填固定值，必须为“cn-hanghou”
    private static String regionId = "cn-hangzhou";

    static {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }

    public AliyunClient() {
    }

    public static IAcsClient getClient() {
        return client;
    }
}
