package com.juzi.Aliyun.dns;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.juzi.dom4j.XmlReader;

/**
 * 修改解析记录（核心代码）
 * Author: Juzi
 * Time: 2018/7/24 17:41
 * Blog: http://juzibiji.top
 */
public class UpdateDNS {
    //客户端
    private IAcsClient client = null;
    //解析记录ID
    private String recordId = XmlReader.getRoot().element("domain").elementText("RecordId");
    //解析记录名
    private String rR = XmlReader.getRoot().element("domain").elementText("RR");
    //解析记录类型
    private String type = XmlReader.getRoot().element("domain").elementText("Type");
    //解析记录值
    private String ip = GetIp.getIp();

    public void setClient(IAcsClient client) {
        this.client = client;
    }

    public void updateDNS() {
        //定义请求
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        UpdateDomainRecordResponse response = null;

        //设置参数
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。

        if (recordId == null||"".equals(recordId)) {
            recordId = FindRecordId.findRecordId(AliyunClient.getClient());
        }
        request.setRecordId(recordId);
        request.setRR(rR);
        request.setType(type);
        request.setValue(ip);

        //发送请求
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            //e.printStackTrace();
        }
    }
}
