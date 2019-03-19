package vip.ifmm.rolesystem.service;

import org.springframework.stereotype.Service;
import vip.ifmm.rolesystem.entity.SysRole;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/13 21:01
 */
@Service
public interface RoleService {

    List<SysRole> listRole();
}
