package com.shearf.cloud.apps.user.center.web.helper;

import com.alibaba.fastjson.JSON;
import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.constants.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/1
 */
public class HttpHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);
    /**
     * 是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestWith = request.getHeader(Constant.X_REQUEST_HEADER);
        return StringUtils.isNotBlank(xRequestWith) && Constant.X_REQUEST_HEADER_VALUE.equals(xRequestWith);
    }

    /**
     * 返回json
     *
     * @param response
     * @param httpServletResponse
     * @return
     */
    public static void responseJson(Response response, HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(String.valueOf(ContentType.DEFAULT_TEXT.withCharset(Charset.forName("UTF-8"))));
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(JSON.toJSONString(response));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("无法返回json请求");
        }
    }
}
