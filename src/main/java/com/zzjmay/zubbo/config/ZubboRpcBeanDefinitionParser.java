package com.zzjmay.zubbo.config;

import com.zzjmay.zubbo.ReferenceBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by zzjmay on 2019/3/14.
 */
public class ZubboRpcBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        return ReferenceBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String interfaceClass = element.getAttribute("interface");

        if(StringUtils.hasText(interfaceClass)){
            builder.addPropertyValue("interfaceClass",interfaceClass);
        }
    }
}
