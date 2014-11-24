package com.ailk.test.spring.testcontext.transaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 1、如果@Transactional声明在类中，则执行顺序是
 * 	--->@BeforeClass
 * 		--->@BeforeTransaction
 * 			--->@Before
 * 				--->@Test （createUserTest测试方法）
 * 			--->@After
 *		--->@AfterTrasaction
 * 		--->@BeforeTransaction
 * 			--->@Before
 * 				--->@Test （otherTest测试方法）
 * 			--->@After
 *		--->@AfterTrasaction
 * 	--->@AfterClass
 * 
 * 2、如果@Transactional仅仅是声明在createUserTest方法中，则执行顺序是
 * 	--->@BeforeClass
 * 		--->@BeforeTransaction
 * 			--->@Before
 * 				--->@Test （createUserTest测试方法）
 * 			--->@After
 *		--->@AfterTrasaction
 * 			--->@Before
 * 				--->@Test （otherTest测试方法）
 * 			--->@After
 * 	--->@AfterClass
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("file:src/main/webapp")
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/webapp/WEB-INF/sample-servlet.xml" }, classes = {})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TransactionTestCase {

	@BeforeClass
	public static void setUpTestDataEnv() {
		System.out.println("@BeforeClass");
		// 准备数据，在事务之内
	}

	@BeforeTransaction
	public void verifyInitialDatabaseState() {
		// 用于在事务执行之前检测数据初始状态
		System.out.println("@BeforeTransaction");
	}

	@Before
	public void setUpTestDataWithinTransaction() {
		System.out.println("@Before");
		// 准备数据，在事务之内
	}

	@Test
	// 覆盖声明在测试类的@TrasactionConfiguration(defaultRollback=...)
	@Rollback(true)
	public void createUserTest() {
		// 测试执行，在事务之内

		// 创建一个用户对象
		// User user = new User();
		// user.sertName("John");

		// 添加一个用户
		// userDao.insertUser(user)

		// 查询出该用户
		// user2 = userDao.getUserById(user.getId())

		// 比较
		// assertEuqals(user.getName(), user2.getName())

		// 测试结束后数据回滚

		System.out.println("modifyDatabaseWithinTransaction");
	}

	@Test
	// 覆盖声明在测试类的@TrasactionConfiguration(defaultRollback=...)
	@Rollback(false)
	public void otherTest() {
		System.out.println("modifyDatabaseWithinTransaction2");
	}

	@After
	public void tearDownWithinTransaction() {
		System.out.println("@After");
		// 执行清理，在事务之内
		// execute "tear down" logic within the transaction
	}

	@AfterClass
	public static void tearDownTestEnv() {
		System.out.println("@AfterClass");
	}

	@AfterTransaction
	public void verifyFinalDatabaseState() {
		System.out.println("@AfterTransaction");

		// 检测rollback后的最终状态，事务之后
	}

}
