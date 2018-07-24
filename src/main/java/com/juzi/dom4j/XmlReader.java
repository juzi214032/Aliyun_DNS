package com.juzi.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * 使用DOM4J读取XML配置文件
 * Author: Juzi
 * Time: 2018/7/24 17:24
 * Blog: http://juzibiji.top
 */
public class XmlReader {
    private static Element root = null;

    public static Element getRoot() {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File("config.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        root = document.getRootElement();
        return root;
    }
}
