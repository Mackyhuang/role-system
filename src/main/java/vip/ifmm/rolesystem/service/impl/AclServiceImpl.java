package vip.ifmm.rolesystem.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vip.ifmm.rolesystem.entity.SysAcl;
import vip.ifmm.rolesystem.mapper.SysAclMapper;
import vip.ifmm.rolesystem.service.AclService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 18:53
 */
@Service
public class AclServiceImpl implements AclService {

    @Resource
    private SysAclMapper sysAclMapper;
    /**
     * 列举所有权限，供shiroConfiguration配置
     *
     * @return
     */
    @Override
    public List<SysAcl> listAcl() {
        List<SysAcl> acls = sysAclMapper.listAcl();
        if (CollectionUtils.isEmpty(acls)){
            return null;
        }
        return acls;
    }

    /**
     * 列举所有权限，供前台查看
     *
     * @return
     */
    @Override
    public List<SysAcl> listAclDetail() {
        List<SysAcl> acls = sysAclMapper.listAclDetail();
        if (CollectionUtils.isEmpty(acls)){
            return null;
        }
        return acls;
    }

    /**
     * 管理员创建权限
     *
     * @param acl
     * @return
     */
    @Override
    public String saveAcl(SysAcl acl) {
        if (StringUtils.isEmpty(acl.getUrl()) || StringUtils.isEmpty(acl.getName())){
            return "参数不完整";
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        acl.setaId(uuid);
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        acl.setOperator(userSessionId);
        acl.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        acl.setAclModuleId("1");
        acl.setOperateIp("127.0.0.1");
        acl.setCode("1");
        acl.setType(1);
        acl.setSeq(1);
        acl.setRemark("1");
        acl.setStatus(1);
        int result = sysAclMapper.saveAcl(acl);
        if (result <= 0){
            return "权限添加失败";
        }
        return "权限添加成功";
    }

    /**
     * 管理员修改权限信息
     *
     * @param acl
     * @return
     */
    @Override
    public String updateAcl(SysAcl acl) {
        if (StringUtils.isEmpty(acl.getaId())){
            return "参数不完整";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        acl.setOperator(userSessionId);
        acl.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        acl.setAclModuleId("1");
        acl.setOperateIp("127.0.0.1");
        System.out.println("mapper update");
        int result = sysAclMapper.updateAcl(acl);
        if (result <= 0){
            return "权限修改失败";
        }
        return "权限修改成功";
    }

    /**
     * 管理员删除权限
     *
     * @param acl
     * @return
     */
    @Override
    public String removeAcl(SysAcl acl) {
        if (StringUtils.isEmpty(acl.getaId())){
            return "参数不完整";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String userSessionId = String.valueOf(session.getAttribute("userSessionId"));
        acl.setOperator(userSessionId);
        acl.setOperateTime(new Date());
        //TODO 获取操作者IP 以及部门模块
        acl.setAclModuleId("123");
        acl.setOperateIp("127.0.0.1");
        int result = sysAclMapper.removeAcl(acl);
        if (result <= 0){
            return "权限删除失败";
        }
        return "权限删除成功";
    }

    /**
     * 获取权限信息
     *
     * @param aId
     * @return
     */
    @Override
    public SysAcl getAcl(String aId) {
        if (StringUtils.isEmpty(aId)){
            return null;
        }
        SysAcl user = sysAclMapper.getAcl(aId);
        return user;
    }


}
