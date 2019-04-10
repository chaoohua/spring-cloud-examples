package com.neo.filter;

import com.neo.utils.AppIdMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;

public class SignatureFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(SignatureFilter.class);

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 1; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("--->>> SignatureFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String key = request.getHeader("App-Key");
        String nonceNum = request.getHeader("Nonce");
        String time = request.getHeader("Timestamp");
        String sign = request.getHeader("Signature");
        final String appKey = !StringUtils.isEmpty(key) ? key
                : request.getHeader("RC-App-Key");
        final String nonce = !StringUtils.isEmpty(nonceNum) ? nonceNum
                : request.getHeader("RC-Nonce");
        final String timestamp = !StringUtils.isEmpty(time) ? time
                : request.getHeader("RC-Timestamp");
        final String signature = !StringUtils.isEmpty(sign) ? sign
                : request.getHeader("RC-Signature");
        logger.info("--->>> SignatureFilter {},{},{},{}", appKey, nonce, timestamp, signature);
        long appId = 0;
        try {
            appId = AppIdMapper.string2long(appKey);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        boolean isSuccess = false;
        try {
            isSuccess = com.neo.utils.StringUtils
                    .validationSignature(nonce, timestamp, signature, appId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isSuccess) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("Signature is error");
            ctx.set("isSuccess", false);
            return null;
        }
    }

}
