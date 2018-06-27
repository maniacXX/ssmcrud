package com.itlc.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 * @author Administrator
 * @date 2018/6/10 15:06
 */

public class Msg {
    //状态码 自己规定：1-成功 0-失败
    private int code;

    //提示信息
    private String msg;

    //用户返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<String,Object>();

    //处理结果
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public Msg setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Msg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public Msg setExtend(Map<String, Object> extend) {
        this.extend = extend;
        return this;
    }
}
