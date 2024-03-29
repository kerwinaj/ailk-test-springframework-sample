package com.ailk.test.spring.testcontext.transaction;

/**
 * 
 * Spring集成测试支持目标之三：支持事务管理
 * 
 * 		当我们进行真实的数据库时，每一个测试用例都会改变数据库数据，这样进行下一次测试时，数据已经改变
 * 
 * 事务管理可以保证一个测试方法的所有数据操作在同一事务中进行，当设置rollback=true时，测试完毕之后
 * 
 * 回滚所有操作，不会对数据有影响
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
