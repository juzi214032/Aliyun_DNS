package com.juzi.Aliyun.dns;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.juzi.dom4j.XmlReader;

import java.util.Iterator;
import java.util.List;

/**
 * 该类通过RR值（解析记录）来获取RecordId
 * Author: Juzi
 * Time: 2018/7/24 18:08
 * Blog: http://juzibiji.top
 */
public class FindRecordId {
    //定义客户端
    private static IAcsClient client = null;
    //定义主域名
    private static String domainName = XmlReader.getRoot().element("domain").elementText("DomainName");
    //解析记录
    private static String rR = XmlReader.getRoot().element("domain").elementText("RR");
    //RecordId
    private static String recordId = null;

    public static String findRecordId(IAcsClient client) {
        //定义请求
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        DescribeDomainRecordsResponse response = null;

        //设置参数
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。

        request.setDomainName(domainName);

        try {
            //发送请求
            response = client.getAcsResponse(request);
            //接收返回的解析记录集合
            List<DescribeDomainRecordsResponse.Record> domainRecords = response.getDomainRecords();
            //获取迭代器
            Iterator<DescribeDomainRecordsResponse.Record> iter = domainRecords.iterator();
            while (iter.hasNext()) {
                DescribeDomainRecordsResponse.Record record = iter.next();
                if (record.getRR().equals(rR)) {
                    recordId = record.getRecordId();
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return recordId;
    }
}
