package com.vmock.base.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 共同工具类
 * <p>
 * 填写方法前，请优先查阅hutool，commonlang3等工具集是否有可用方法。
 *
 * @author vt
 */
@UtilityClass
public class CommonUtils {

    // ------------------- 请求相关 ----------------------------

    private static final String XHR = "XMLHttpRequest";

    private static final String[] ASYNC_FORMAT = {".json", ".xml"};

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }


    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }


    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }


    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjAx(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf(ContentType.JSON.toString()) != -1) {
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");

        if (xRequestedWith != null && xRequestedWith.indexOf(XHR) != -1) {
            return true;
        }
        String uri = request.getRequestURI();
        if (StrUtil.containsAny(uri, ASYNC_FORMAT)) {
            return true;
        }
        String ajax = request.getParameter("__ajax");
        if (StrUtil.containsAny(ajax, ASYNC_FORMAT)) {
            return true;
        }
        return false;
    }

    // ------------------- 类型转换 ----------------------------
    public static List<Long> splitToIdList(String str, CharSequence separator) {
        if (StrUtil.isNotEmpty(str) && separator != null) {
            String[] strArr = str.split(separator.toString());
            // 转为list
            ArrayList<Long> idList = new ArrayList<>(strArr.length);
            for (String item : strArr) {
                idList.add(Long.valueOf(item));
            }
            return idList;
        }
        return Collections.emptyList();
    }

}
