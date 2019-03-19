package com.hug.web.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hug.dao.bean.User;
import com.hug.web.aspect.TestAspect;

/**
 * @author Kael
 *
 */
@Controller
@RequestMapping("/spring")
public class HelloWorldController {

	private static final String HELLO = "hello";

	@Autowired
	private TestAspect testAspect;

	/**
	 * 1.使用@RequestMapping注解来映射请求的URL
	 * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下的解析：
	 * 通过prefix+returnVal+suffix这样的方式得到实际的物理视图。然后会做转发操作
	 *
	 * /WEB-INF/views/hello.jsp
	 *
	 *
	 * @return
	 */
	// ? : 匹配一个字符
	// * : 匹配任意字符
	// ** : 匹配多层路径
	@RequestMapping("/helloworld")
	public String helloworld(ServletContext servletContext) {
		System.out.println("servletContext : " + servletContext);
		System.out.println(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		System.out.println("applicationContext : " + applicationContext);
		return HELLO;
	}

	@SuppressWarnings("null")
	@RequestMapping("/file")
	public String file(String path) {
		System.out.println("=-=-=-=-" + path);
		// 制造异常
		String demo = null;
		demo.equals("");
		return HELLO;
	}

	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return HELLO;
	}

	/**
	 * en-US
	 *
	 * @return
	 */
	@RequestMapping(value = "/testParamsAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return HELLO;
	}

	/**
	 * * @RequestMapping 支持 Ant风格资源地址支持3种匹配符
	 *
	 * ？：匹配文件名中的一个字符
	 *
	 * *：匹配文件名中的任意字符
	 *
	 * **：**匹配多层路径
	 *
	 * @return
	 */
	@RequestMapping("/testAnt/*/ant?")
	public String testAnt() {
		testAspect.testAspect("demo");
		System.out.println("/testAnt/*/ant");
		return HELLO;
	}

	/**
	 * 占位符
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") String id, String add) {
		System.out.println(add);
		System.out.println("testPathVariable" + id);
		return HELLO;
	}

	/**
	 * rest repersentational state transfer (资源)表现层状态转化。是目前最流行的一种互联网软件构架
	 *
	 * 以CRUE为例：
	 *
	 * 新增： /order post 修改： /order/1 put update?id=1 获取：/order/1 get 删除：/ order/1
	 * delete
	 *
	 * 如何发送put和delete请求
	 *
	 * 1.配置HiddenHttpMethodFilter 2.需要发送post请求
	 * 3.body必须携带name=_method，值为put或者delete
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRestGET(@PathVariable int id) {
		System.out.println("test Rest GET:" + id);
		return HELLO;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.POST)
	public String testRestPOST(@PathVariable int id) {
		System.out.println("test Rest POST:" + id);
		return HELLO;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDELETE(@PathVariable int id) {
		System.out.println("test Rest DELETE:" + id);
		return HELLO;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPUT(@PathVariable int id) {
		System.out.println("test Rest PUT:" + id);
		return HELLO;
	}

	/**
	 * required默认是true
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		System.out.println("test requestParam:" + age);
		return HELLO;
	}

	/**
	 * 映射请求头信息
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(
			@RequestHeader(value = "Accept-Language", required = false, defaultValue = "0") String al) {
		System.out.println("test requestHeader:" + al);
		return HELLO;
	}

	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionid) {
		System.out.println("test cookieValue:" + sessionid);
		return HELLO;
	}

	@RequestMapping("/testPOJO")
	public String testPOJO(User user, String nickname) {
		System.out.println("test testPOJO:" + user);
		System.out.println("test testPOJO:" + nickname);
		return HELLO;
	}

	/**
	 * 可以使用servlet原生的API作为目标方法的参数 具体支持一下类型
	 *
	 * {@link HttpServletRequest}
	 *
	 * {@link HttpServletResponse}
	 *
	 * {@link HttpSession}
	 *
	 * {@link Principal}
	 *
	 * {@link Locale}
	 *
	 * {@link InputStream}
	 *
	 * {@link OutputStream}
	 *
	 * {@link Reader}
	 *
	 * {@link Writer}
	 *
	 */
	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("test request:" + request);
		System.out.println("test response:" + response);
		return HELLO;
	}

	

}
