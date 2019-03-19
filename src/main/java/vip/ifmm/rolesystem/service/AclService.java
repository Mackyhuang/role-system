package vip.ifmm.rolesystem.service;

import vip.ifmm.rolesystem.entity.SysAcl;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 18:52
 */
public interface AclService {

    /**
     * 列举所有权限，供shiroConfiguration配置
     * @return
     */
    List<SysAcl> listAcl();

    /**
     * 列举所有权限，供前台查看
     * @return
     */
    List<SysAcl> listAclDetail();

    /**
     * 管理员创建权限
     * @return
     */
    String saveAcl(SysAcl acl);

    /**
     * 管理员修改权限信息
     * @param acl
     * @return
     */
    String updateAcl(SysAcl acl);

    /**
     * 管理员删除权限
     * @param acl
     * @return
     */
    String removeAcl(SysAcl acl);

    /**
     * 获取权限信息
     * @param aId
     * @return
     */
    SysAcl getAcl(String aId);
}
