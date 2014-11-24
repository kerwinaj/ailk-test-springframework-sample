package com.ailk.test.spring.testcontext.appcontext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 因为@ContextConfiguration不能同时指定classes和locations来属性，如果要混合使用xml配置和注解配置，
 * 可以先设置@ContextConfiguration的locations，并在xml文件配置成注解驱动扫描配置
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "app-context.xml" })
public class MixXmlAndAnnotationTestCase {

}
