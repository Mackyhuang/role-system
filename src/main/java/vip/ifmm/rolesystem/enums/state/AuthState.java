package vip.ifmm.rolesystem.enums.state;


import vip.ifmm.rolesystem.enums.Statable;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 21:55
 */
public enum AuthState implements Statable {

    SUCCESS(Statable.SUCCESS_CODE, "操作成功"),
    USER_GET_FAILED(-1001, "用户信息读取失败");

    private int code;
    private String msg = null;

    AuthState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
