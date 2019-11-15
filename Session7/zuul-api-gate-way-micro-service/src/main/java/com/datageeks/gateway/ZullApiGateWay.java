package com.datageeks.gateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZullApiGateWay extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(ZullApiGateWay.class);

	@Override
	public boolean shouldFilter() {

		return true; // The return value can be decided based on user request. if the value is true then only filter executes.
	}
;
	@Override
	public Object run() throws ZuulException {

	HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
	String accessToken  = request.getHeader("access-token");
	log.info(" Zull API Gate Way ***** path "+request.getServletPath());
	log.info(" Access token ******"+accessToken);
	log.info(" Content type  ******"+request.getHeader("content-type"));	
		if(accessToken == null ||  !accessToken.equals("982u3hssdhyituhst8ytq72623"))
				throw new ZuulException("Athenication Failed", 403, "Invalid Access Token");
			
	return null;
	}

	@Override
	public String filterType() {

		return "pre"; /* can be pre , post, error. */
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;// We can enable more than on api gate way , we can mention execution order .order number starts from 0
	}

}
