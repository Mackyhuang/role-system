package vip.ifmm.rolesystem.mapper;

import org.junit.Test;
import vip.ifmm.rolesystem.entity.SysUser;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/14 16:07
 */
public class SysUserMapperTest {

    @Resource
    private SysUserMapper userMapper;

    @Test
    public void getUserByUsername() {
        SysUser macky = userMapper.getUserByUsername("macky");
        System.out.println(macky.getRoles());

    }
}