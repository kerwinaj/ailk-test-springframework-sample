package com.ailk.test.spring.testcontext.mocks.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ailk.springsample.controller.IndexController;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 	独立于WebApplicationContext的MockMvc实例，不应指定@RunWifth为SpringJUnit4ClassRunner
 * </pre>
 * @date 2013-1-24
 */
public class StandaloneSetupTestCase {
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new IndexController()).build();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc = standaloneSetup(new IndexController()).build();
		ResultActions actions = this.mockMvc.perform(get("/index"));
		actions.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(view().name("main"))
				.andExpect(handler().methodName("index"))
				.andExpect(handler().handlerType(IndexController.class));
	}
}