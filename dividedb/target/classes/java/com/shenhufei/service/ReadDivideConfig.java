package com.shenhufei.service;

import com.shenhufei.base.DivideDBConfig;
import com.shenhufei.enums.DivideConfigAttributeEnum;
import com.shenhufei.enums.DividelabelEnum;
import com.shenhufei.exception.DivideDbConfigFileNotFoundException;
import com.shenhufei.exception.PojoClassNotFoundException;
import com.shenhufei.pojo.DivideConfigPojo;
import com.shenhufei.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   
 * 
 * @Description: 读取xml配置文件中的分表配置信息；
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
public class ReadDivideConfig  {
	
    public static List<DivideConfigPojo> getListConfig()
            throws DocumentException, DivideDbConfigFileNotFoundException {
        // 1.获得document文档对象
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(DivideDBConfig.CONFIGPATH);
        } catch (Exception e) {
            throw new DivideDbConfigFileNotFoundException(
                    "divide.xml can not found");
        }
        // 2.获取根元素
        Element rootElement = doc.getRootElement();
        // 3.获取根元素下的子元素
        List<Element> childElements = rootElement.elements();
        // 4.遍历子元素
        List<DivideConfigPojo> list = new ArrayList<DivideConfigPojo>();
        for (Element element : childElements) {
            // 5.判断元素名称为database的元素l
            if (DividelabelEnum.DATABASE.getType().equals(element.getName())) {
                // 6.获取pojo元素
                DivideConfigPojo config = getConfig(element);
                list.add(config);
            }
        }
        return list;

    }

    private static DivideConfigPojo getConfig(Element element) {
        DivideConfigPojo config = new DivideConfigPojo();
        Element pojo = element.element(DividelabelEnum.POJO.getType());
        // 需要去校验 这个类路径是否存在；
        String value = pojo
                .attributeValue(DivideConfigAttributeEnum.pojo.getType());
        Element element2 = pojo.element(DividelabelEnum.FIELD.getType());
        String field = element2
                .attributeValue(DivideConfigAttributeEnum.name.getType());
        String part = element2
                .attributeValue(DivideConfigAttributeEnum.part.getType());
        config.setDataDbName(element.getName());
        config.setPojoName(value);
        config.setField(field);
        config.setPart(Integer.parseInt(part));
        return config;
    }

    /**
     * 解析实体类中的分表配置参数
     * 
     * @author shenhufei
     *
     * @throws DocumentException
     * @throws ClassNotFoundException
     */
    public static Map<String, HashMap<String, String>> resolveConfigList()
            throws DocumentException, ClassNotFoundException {
        Map<String, HashMap<String, String>> mapList = new HashMap<String, HashMap<String, String>>();
        List<DivideConfigPojo> list = getListConfig();
        for (DivideConfigPojo pojo : list) {
            // 判断这个实体类对应的表是否存在
            String pojoName = pojo.getPojoName();
            Class<?> forName = Class.forName(pojoName);
            if (null == forName) {
                throw new PojoClassNotFoundException(
                        pojoName + "can not find in pojo package");
            }
            Field[] fields = forName.getDeclaredFields();
            HashMap<String, String> map = getField(fields);
            mapList.put(pojoName, map);
        }
        return mapList;
    }

    public static HashMap<String, String> getField(Field[] fields) {
        HashMap<String, String> map = new HashMap<String, String>();
        for (Field field : fields) {
            String fieldName = field.getName();
            Type fieldType = field.getGenericType();
            String fieldT = null;
            if (null != fieldType) {
                fieldT = StringUtils.getFieldDataType(fieldType.getTypeName());
            }
            // 根据igonre的注解，判断如果有这个注解的话，不做新增字段；
            Boolean igonre = true;
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length != 0) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().toGenericString()
                            .equals(DivideDBConfig.IGONRECLASS)) {
                        igonre = false;
                    }
                }
            }
            if (igonre) {
                map.put(fieldName, fieldT);
            }
        }
        return map;
    }

	

}
