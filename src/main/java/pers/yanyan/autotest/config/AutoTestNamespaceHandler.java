package pers.yanyan.autotest.config;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by yan on 16/5/4.
 */
public class AutoTestNamespaceHandler extends NamespaceHandlerSupport {
    public static final String PAGE = "page";


    public void init() {
        registerBeanDefinitionParser(PAGE, new PageDefinitionParser());
    }

    private abstract class AutoTestDefinitionParser extends
            AbstractBeanDefinitionParser {

        protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                    .genericBeanDefinition();

            Class<?> beanClass = getBeanClass(element);
            if (beanClass != null) {
                builder.getRawBeanDefinition().setBeanClass(beanClass);
            }

            builder.getRawBeanDefinition().setSource(parserContext.extractSource(element));

            doParse(element, parserContext, builder);
            return builder.getBeanDefinition();
        }

        protected abstract void doParse(Element element, ParserContext parserContext,
                                        BeanDefinitionBuilder builder);

        protected abstract Class<?> getBeanClass(Element element);
    }

    private class PageDefinitionParser extends AutoTestDefinitionParser {

        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        }

        protected Class<?> getBeanClass(Element element) {
            return null;
        }
    }
}
