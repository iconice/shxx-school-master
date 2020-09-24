package com.sjht.school.mobile.filter;

import com.alibaba.fastjson.JSONObject;
import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.enums.RespCodeEnums;
import com.sjht.school.football.service.mobile.MobileLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * **************************************************
 * @ClassName UrlFilter
 * @Description 判断用户是否登录过期服务器
 * @Author maojianyun
 * @Date 2019/11/7 10:12
 * @Version V1.0
 * ****************************************************
 **/
@Component
public class TokenFilter implements Filter {

    private final int time = 20*24*60*60;

    @Autowired
    private MobileLoginService mobileLoginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 添加参数，允许任意domain访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 这个allow-headers要配为*，这样才能允许所有的请求头 --- update by zxy  in 2018-10-19
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Max-Age", time+"");
        Enumeration headers = req.getHeaders("token");
        String key = "token".trim();
        String token = req.getHeader(key);
        System.err.println(req.getRequestURI() + "_" + token);
        if (StringUtils.isNotBlank(token)) {
            ObjectResponse objectResponse = mobileLoginService.getMobileUser(token);
            if (RespCodeEnums.LOGIN_EXPIRATION_CODE.getCode() == objectResponse.getCode()) {
                // 用户登录过期处理
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.error(RespCodeEnums.LOGIN_EXPIRATION_CODE.getCode(), RespCodeEnums.LOGIN_EXPIRATION_CODE.getMsg());
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(JSONObject.toJSONString(baseResponse));
                // req.getRequestDispatcher("/common/overdue").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            // req.getRequestDispatcher("/common/tokenIsNull").forward(request, response);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.error(RespCodeEnums.TOKEN_IS_NULL_CODE.getCode(), RespCodeEnums.TOKEN_IS_NULL_CODE.getMsg());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(baseResponse));
        }

    }
    @Override
    public void destroy() {

    }
}
