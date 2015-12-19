package sample.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter
{
	
	@Autowired
	private DataSource datasource;
		
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (modelAndView != null)
        {
            ServletRequest req = (ServletRequest) request;
            ServletResponse resp = (ServletResponse) response;
            FilterInvocation filterInvocation = new FilterInvocation(req, resp, new FilterChain()
            {
                public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException
                {
                    throw new UnsupportedOperationException();
                }
            });
 
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null)
            {
                WebSecurityExpressionRoot sec = new WebSecurityExpressionRoot(authentication, filterInvocation);
                sec.setTrustResolver(new AuthenticationTrustResolverImpl());
                
                modelAndView.getModel().put("sec", sec);
            }
            if(!modelAndView.getViewName().startsWith("redirect:")){
            	
	            System.out.println("-----------------------------------");
            	System.out.println("View Name : " + modelAndView.getViewName());
            	
            	String path="";
            	String view=modelAndView.getViewName();
            	
            	String[] parts = modelAndView.getViewName().split("@");
            	if (parts.length>1) {
            		path = parts[0];
            		view = parts[1];
            	}
	            modelAndView.getModel().put("view", path+"/"+view);
	            modelAndView.setViewName(path+"/master");

            	System.out.println("View      : " + path+"/"+view);
            	System.out.println("Path      : " + path+"/master");
	            System.out.println("-----------------------------------");            	
            }
        }
    }
}
