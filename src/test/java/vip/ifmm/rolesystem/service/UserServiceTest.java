package vip.ifmm.rolesystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vip.ifmm.rolesystem.entity.SysUser;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 20:42
 */
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void getUserByUsername() {
        SysUser macky = service.getUserByUsername("macky");
        System.out.println(macky.getuId());

    }
}