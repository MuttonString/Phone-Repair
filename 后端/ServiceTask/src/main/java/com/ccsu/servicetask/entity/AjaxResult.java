package com.ccsu.servicetask.entity;

public class AjaxResult {
    int code;
    String msg;
    Object data;

    public static AjaxResult success(int code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static AjaxResult success(int code, String msg) {
        AjaxResult result = new AjaxResult();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static AjaxResult success(int code, Object data) {
        return success(code, "成功", data);
    }

    public static AjaxResult success(Object data) {
        return success(1, data);
    }

    public static AjaxResult success(String msg) {
        return success(1, msg);
    }

    public static AjaxResult fail(int code, String msg) {
        AjaxResult result = new AjaxResult();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static AjaxResult fail(String msg) {
        return fail(0, msg);
    }
}
