package vip.ifmm.rolesystem.dto;


import vip.ifmm.rolesystem.enums.Statable;

/**
 * 通用服务器响应 DTO 类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/11/12 21:54:23
 */
public class ServerResponse<T> {

    private int code;
    private String msg = null;
    private T data = null;

    private ServerResponse(Statable statable, T data) {
        this.code = statable.getCode();
        this.msg = statable.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 返回服务器响应
    public static <T> ServerResponse<T> response(Statable statable) {
        return ServerResponse.response(statable, null);
    }

    // 返回服务器响应
    public static <T> ServerResponse<T> response(Statable statable, T data) {
        return new ServerResponse<T>(statable, data);
    }

    // 判断是否操作成功
    public boolean isSuccess() {
        return (code == Statable.SUCCESS_CODE);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
