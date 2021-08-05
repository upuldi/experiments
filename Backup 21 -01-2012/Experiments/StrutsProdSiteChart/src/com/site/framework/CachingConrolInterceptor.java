package com.site.framework;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/18/11
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class CachingConrolInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {


        ActionContext ctx = actionInvocation.getInvocationContext();
        HttpServletResponse response = (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);

        System.out.println(" CONTENT TYPE FOUND ..... " + response.getContentType() );

        return actionInvocation.invoke();


    }


}
