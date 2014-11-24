package com.ailk.test.spring.testcontext.di;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ailk.test.spring.CustomDataSourceConfig;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 可以从ApplicationContext自动注入dataSource
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CustomDataSourceConfig.class })
public class DITestCase {

	@Autowired
	DataSource dataSource;

	@Test
	public void testDataSource() {
		Assert.assertNotNull(dataSource);
	}
}
