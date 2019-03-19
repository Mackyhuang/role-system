package vip.ifmm.rolesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.ifmm.rolesystem.entity.SysAcl;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 18:42
 */
public interface SysAclMapper {

    List<SysAcl> listAcl();

    List<SysAcl> listAclDetail();

    int saveAcl(SysAcl acl);

    int updateAcl (SysAcl acl);

    int removeAcl(SysAcl acl);

    SysAcl getAcl(String acl);
}
