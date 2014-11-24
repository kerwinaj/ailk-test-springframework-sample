package com.ailk.test.spring.testcontext.mocks;

/**
 * Spring MVC Test Framework
 * 
 * 用来测试Spring MVC web项目的一个测试框架，它模拟了大部分的Servlet API。在实际测试时，通过构建一个MockHttpServletRequest发送请求，
 * 
 * 结束后返回一个MockHttpServletResponse响应，整个测试过程也无需运行在一个真实的Servlet容器中。对于JSP视图，MockHttpServletResponse 
 * 
 * 也不会真正的执行转发（forward）或重定向（redirect），而是把URL保存下来，测试时只要验证URL是否正确即可。对于JSON或各种类型的除JSP试图（view)，
 * 
 * 例如 Freemarker, Velocity，MockHttpServletResponse会在content里包含这些数据，验证这些数据即可。
 * 
 * 
 * 核心类：
 * 
 * 1、MockHttpServletRequest，模拟一个http请求，可以设置请求的URL，设置用户传入参数，编码格式，HTTP头部信息等
 * 
 * 2、MockHttpServletResponse，模拟一个http响应，保存响应的视图，用户model数据，响应头部信息，响应内容等。
 * 
 * 3、MockMvc，模拟整个MVC框架，构建MockHttpServletRequest，使用DispatcherServlet执行链处理请求，返回MockHttpServletResponse响应。
 * 
 * 4、MockHttpServletRequestBuilder，MockHttpServletRequest请求构建器，创建一个mock请求时，一般不通过new一个MockHttpServletRequest对象并设置这个对象的
 * 
 *    各种属性，而是通过MockHttpServletRequestBuilder来构建。
 *    
 * 5、MockMvcRequestBuilders，提供了很方便的静态方法，用于创建不同请求构建器MockHttpServletRequestBuilder，这些构建器分别是用来创建get/post/delete/put请求构建器
 * 
 * 6、ResultMatcher，响应结果的各个属性是通过MockMvcResultMatcher验证的，视图有视图的ResultMatcher，响应内容有响应内容的ResultMatcher等。
 * 
 * 7、各种ResultMatcher簇，
 * 		（1）ContentResultMatchers，响应内容ResultMatcher簇，验证二进制/字符串/HTML(DOMSource)/xml形式内容及contentType。
 * 		（2）ViewResultMatchers，视图ResultMatcher簇，验证视图的名字
 * 		（3）CookieResultMatchers，Cookies ResultMatcher簇，验证Cookie的各种属性domain/expired time/cookie/comment值等
 * 		（4）FlashAttributeResultMatchers，验证response的attribute值
 * 		（5）JsonPathResultMatchers，JSON响应内容ResultMatcher簇，验证JSON格式的各个值
 * 		（6）HeaderResultMatchers，响应头信息ResultMatcher簇，验证响应头的键值信息，
 * 		（7）ModelResultMatchers，Model ResultMatcher簇，验证Model的attribute键值信息及是否包含错误
 *		（6）RequestResultMatchers，请求ResultMatcher簇，验证请求是否异步/请求attribute/session attribute
 * 		（8）StatusResultMatchers，响应状态码验ResultMatcher簇，验证响应的状态码
 * 		（9）XpathResultMatchers，响应内容ResultMatcher簇，通过指定xpath验证响应内容
 * 		（10）HandlerResultMatchers，处理器（Controller）ResultMatcher簇，验证被调用的处理器类/处理方法

 * 8、MockMvcResultMatchers，提供了很方便的静态方法，用于创建7、中提到的各种ResultMatcher簇
 * 
 * 9、MockMvcBuilders，提供2个静态方法，用于创建MockMvc对象
 *		（1）、standalone方式，无需加载WebApplicationContext，其依赖需要模拟（mock）或者手工实例化，这种方式接近单元测试
 *		（2）、webAppContext方法，需要加载WebApplicationContext，通过WebApplicationContext加载Controller及其各种依赖，这种方式接近集成测试
 * 		
 * 
 * 
 * 核心类关系：
 * 
 * 1、MockMvc的创建
 * 
 * 		MockMvcBuilders-----|------standaloneSetup()----------->MockMvc
 * 							|
 * 							|------webAppContextSetup(wac)----->MockMvc
 * 
 * 2、ResultMatcher的创建
 * 
 * 		MockMvcRequestBuilders-----|------content()----------------->ContentResultMatchers----------|----string(content)----->ResultMatcher
 * 							       |                                                                |
 * 							       |                                                                |----encoding(encoding)-->ResultMatcher
 * 							       |                                                                |
 * 							       |                                                                |----....
 * 							       |
 * 							       |
 * 							       |------view()-------------------->ViewResultMatcher--------------|----name(viewName)----->ResultMatcher
 * 							       |                                                                |
 * 							       |                                                                |----name(matcher)-->ResultMatcher
 * 							       |
 * 							       |
 * 							       |------cookie()------------------>CookieResultMatchers-----------|----comment(name,comment)----->ResultMatcher
 * 							       |                                                                |
 * 							       |                                                                |----domain(name,domain)-->ResultMatcher
 * 							       |                                                                |
 * 							       |                                                                |----....
 * 							       |
 * 							       |------flash()------------------->FlashAttributeResultMatchers
 * 							       |
 * 							       |------forwardedUrl(url)--------->ResultMatcher
 * 							       |
 * 							       |------handler()----------------->HandlerResultMatchers
 * 							       |
 * 							       |------header()------------------>HeaderResultMatchers
 * 							       |
 * 							       |------jsonPath(expr,args)------->JsonPathResultMatchers
 * 							       |
 * 							       |------model()------------------->ModelResultMatchers
 * 							       |
 * 							       |------redirectedUrl(url)-------->ResultMatcher
 * 							       |
 * 							       |------request()----------------->RequestResultMatchers
 * 							       |
 * 							       |------status()------------------>StatusResultMatchers
 * 							       |
 * 							       |------xpath(xpath,args)--------->XpathResultMatchers
 * 							       |
 * 
 * 3、MockHttpServletRequest的创建
 * 		
 * 		MockMvcRequestBuilders-----|------get(url)----------|---------->MockHttpServletRequestBuilder--------|-----buildRequest(servletContext)------->MockHttpServletRequest
 * 		                           |                        |
 * 		                           |------post(url)---------|
 * 		                           |                        |
 * 		                           |------put(url)----------|
 * 		                           |                        |
 * 		                           |------delete(url)-------|
 * 		                           |                        |
 * 		                           |------fileUpload(url)---|
 * 
 * 4、发送MockHttpServletRequest及验证（assert)
 * 
 * 		MockMvc-----|------>perform(MockHttpServletRequestBuilder)-------->ResultActions-----|------andExcept(Matcher)------>ResultActions-->...
 *                                                                                           |
 *                                                                                           |------andDo(ResultHandler)---------->ResultActions
 *                                                                                           |
 *                                                                                           |------andReturn(ResultHandler)---------->MvcResult
 * 
 * 
 * 
 */
