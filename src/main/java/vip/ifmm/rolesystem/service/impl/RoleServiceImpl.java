package vip.ifmm.rolesystem.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import vip.ifmm.rolesystem.entity.SysRole;
import vip.ifmm.rolesystem.mapper.SysRoleMapper;
import vip.ifmm.rolesystem.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/13 21:02
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> listRole() {
        List<SysRole> roleList = roleMapper.listRole();
        if (CollectionUtils.isEmpty(roleList)){
            return null;
        }
        return roleList;
    }
}
