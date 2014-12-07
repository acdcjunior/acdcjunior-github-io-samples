package net.acdcjunior.piloto.test;

import java.util.Locale;

import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class DoMockMvc {
	
	public MockMvc go(Object... controllers) {
		XmlWebApplicationContext xml = new XmlWebApplicationContext();
		xml.setConfigLocation("file:src/main/webapp/WEB-INF/applicationContext-SpringMVC.xml");
		xml.setServletContext(new MockServletContext("file:src/main/webapp"));
		xml.refresh();
		final ViewResolver bean = xml.getBean(ViewResolver.class);
		final RequestMappingHandlerMapping rmhp = xml.getBean(RequestMappingHandlerMapping.class);
		
		ViewResolver bridgeViewResolver = new ViewResolver() {
			@Override public View resolveViewName(String viewName, Locale locale) throws Exception {
				return bean.resolveViewName(viewName, locale);
			}
		};
		return MockMvcBuilders.standaloneSetup(controllers)
				.set
				.setUseSuffixPatternMatch(rmhp.useSuffixPatternMatch())
				.setViewResolvers(bridgeViewResolver).build();
	}

}
