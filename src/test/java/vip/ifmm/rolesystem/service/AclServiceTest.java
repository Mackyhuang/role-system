package vip.ifmm.rolesystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vip.ifmm.rolesystem.entity.SysAcl;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 20:32
 */
public class AclServiceTest {

    @Autowired
    private AclService aclService;

    @Test
    public void listAcl() {
        List<SysAcl> sysAcls = aclService.listAcl();
        System.out.println(sysAcls.size());
    }
}