package vip.ifmm.rolesystem.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vip.ifmm.rolesystem.dto.ServerResponse;
import vip.ifmm.rolesystem.entity.SysUser;
import vip.ifmm.rolesystem.enums.state.AuthState;
import vip.ifmm.rolesystem.mapper.SysUserMapper;
import vip.ifmm.rolesystem.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/11 23:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper userMapper;

    /**
     * 通过用户名获取该用户的所有角色以及权限
     *
     * @param username
     * @return
     */
    @Override
    public SysUser getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)){
//            return ServerResponse.response(AuthState.USER_GET_FAILED);
            return null;
        }
        return userMapper.getUserByUsername(username);
    }

    /**
     * 获取所有用户的信息
     *
     * @return
     */
    @Override
    public List<SysUser> listUser() {
        List<SysUser> userList = userMapper.listUser();
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList;
    }

    /**
     * 管理员创建用户
     *
     * @return
     */
    @Override
    public String saveUser(SysUser user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return "参数不完整";
        }
//        StringUtils.isEmpty(user.getDeptId())
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setuId(uuid);
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        user.setOperator(userSessionId);
        user.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        user.setDeptId("123");
        user.setOperateIp("127.0.0.1");
        int result = userMapper.saveUser(user);
        if (result <= 0){
            return "用户添加失败";
        }
        return "用户添加成功";
    }

    /**
     * 管理员修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public String updateUser(SysUser user) {
        if (StringUtils.isEmpty(user.getuId())){
            return "参数不完整";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        user.setOperator(userSessionId);
        user.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        user.setDeptId("123");
        user.setOperateIp("127.0.0.1");
        int result = userMapper.updateUser(user);
        if (result <= 0){
            return "用户修改失败";
        }
        return "用户修改成功";
    }

    /**
     * 管理员删除用户
     *
     * @param user
     * @return
     */
    @Override
    public String removeUser(SysUser user) {
        if (StringUtils.isEmpty(user.getuId())){
            return "参数不完整";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        user.setOperator(userSessionId);
        user.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        user.setDeptId("123");
        user.setOperateIp("127.0.0.1");
        int result = userMapper.removeUser(user);
        if (result <= 0){
            return "用户删除失败";
        }
        return "用户删除成功";
    }

    /**
     * 获取用户信息
     *
     * @param uId
     * @return
     */
    @Override
    public SysUser getUser(String uId) {
        if (StringUtils.isEmpty(uId)){
            return null;
        }
        SysUser user = userMapper.getUser(uId);
        return user;
    }
}
