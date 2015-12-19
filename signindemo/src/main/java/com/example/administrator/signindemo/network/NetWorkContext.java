package com.example.administrator.signindemo.network;

import java.util.Map;

/**
 * ---------------------------------------------------
 * Description: 用于网络访问的参数上下文类
 * Author: ljgsonx
 * Belong to: com.example.administrator.signindemo.network
 * Time: 2015/12/17 14:28
 * ---------------------------------------------------
 */
public class NetWorkContext {

    public String requestMethod = NetWorkConstant.Request.GET;

    public String LOGIN_URL = NetWorkConstant.HOST + "/user/login";
    public String GET_USERINFO = NetWorkConstant.HOST+ "/user/userid";

    public Map<String, Object> params = null;
}
