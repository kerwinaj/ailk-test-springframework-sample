package com.ailk.test.spring.testcontext.testsupportclass;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 	AbstractTransactionalJUnit4SpringContextTests又继承AbstractJUnit4SpringContextTests
 * 并包含了jdbcTemplate对象用于常用的数据操作和一些由有用的方法，为我们操作数据库带来方便
 * </pre>
 * @date 2013-1-23
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/webapp/WEB-INF/sample-servlet.xml" }, classes = {})
public class ExtendedJUnit4SpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void test() {
		assertNotNull(this.applicationContext);
		assertNotNull(this.jdbcTemplate);
		
		// this.countRowsInTableWhere(tableName, whereClause)
		// this.countRowsInTable(tableName)
		// this.deleteFromTables(names)
		// this.dropTables(names);
		// this.executeSqlScript(sqlResourcePath, continueOnError);
	}
}
