package vip.ifmm.rolesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ifmm.rolesystem.entity.SysUser;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/11 22:15
 */
public interface SysUserMapper {

    SysUser getUserByUsername(@Param("username") String username);

    List<SysUser > listUser();

    int saveUser(SysUser user);

    int updateUser(SysUser user);

    int removeUser(SysUser user);

    SysUser getUser(String uId);
}
