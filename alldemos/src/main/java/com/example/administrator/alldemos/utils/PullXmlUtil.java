package com.example.administrator.alldemos.utils;

import android.util.Xml;

import com.example.administrator.alldemos.beans.DataEntity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Description: pull解析器的操作封装类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/18 16:16
 * ---------------------------------------------------
 */
public class PullXmlUtil {
    /**
     * 使用pull解析器生成xml文件
     *
     * @param dataEntities
     * @param writer
     * @return
     */
    public static void writeXML(List<DataEntity> dataEntities, Writer writer){
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            //第一个参数为命名空间,如果不使用命名空间,可以设置为null
            serializer.startTag("", "dataEntities");
            for (DataEntity dataEntity : dataEntities){
                serializer.startTag("", "dataEntity");
                serializer.startTag("", "id");
                serializer.text(dataEntity.getId() + "");
                serializer.endTag("", "id");
                serializer.startTag("", "name");
                serializer.text(dataEntity.getName());
                serializer.endTag("", "name");
                serializer.startTag("", "imgId");
                serializer.text(dataEntity.getImgId() + "");
                serializer.endTag("", "imgId");
                serializer.startTag("", "imgIntro");
                serializer.text(dataEntity.getImgIntro());
                serializer.endTag("", "imgIntro");
                serializer.endTag("", "dataEntity");
            }
            serializer.endTag("", "dataEntities");
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用pull解析器解析xml文件
     *
     * @param inStream
     * @return  解析xml后的所有元素的list集合
     * @throws Exception
     */
    public static List<DataEntity> readXML(InputStream inStream) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inStream, "UTF-8");
        int eventType = parser.getEventType();
        DataEntity currentEntity = null;
        List<DataEntity> dataEntities = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                    dataEntities = new ArrayList<DataEntity>();
                    break;
                case XmlPullParser.START_TAG:// 开始元素事件
                    String parserName = parser.getName();
                    if (parserName.equalsIgnoreCase("id")) {
                        currentEntity = new DataEntity();
                        currentEntity.setId(new Integer(parser.nextText()));
                    } else if (currentEntity != null) {
                        if (parserName.equalsIgnoreCase("name")) {
                            currentEntity.setName(parser.nextText());// 如果后面是Text元素,即返回它的值
                        } else if (parserName.equalsIgnoreCase("imgId")) {
                            currentEntity.setImgId(new Integer(parser.nextText()));
                        } else if(parserName.equalsIgnoreCase("imgIntro")){
                            currentEntity.setImgIntro(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:// 结束元素事件
                    if (parser.getName().equalsIgnoreCase("dataEntity")&& currentEntity != null) {
                        dataEntities.add(currentEntity);
                        currentEntity = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        inStream.close();
        return dataEntities;
    }
}
