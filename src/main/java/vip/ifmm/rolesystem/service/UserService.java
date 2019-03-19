package vip.ifmm.rolesystem.service;

import vip.ifmm.rolesystem.entity.SysUser;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/11 23:42
 */
public interface UserService {

    /**
     * 通过用户名获取该用户的所有角色以及权限
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);

    /**
     * 获取所有用户的信息
     * @return
     */
    List<SysUser> listUser();

    /**
     * 管理员创建用户
     * @return
     */
    String saveUser(SysUser user);

    /**
     * 管理员修改用户信息
     * @param user
     * @return
     */
    String updateUser(SysUser user);

    /**
     * 管理员删除用户
     * @param user
     * @return
     */
    String removeUser(SysUser user);

    /**
     * 获取用户信息
     * @param uId
     * @return
     */
    SysUser getUser(String uId);
}
