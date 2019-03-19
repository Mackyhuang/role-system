package vip.ifmm.rolesystem.mapper;

import org.junit.Test;
import vip.ifmm.rolesystem.entity.SysAcl;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 21:15
 */
public class SysAclMapperTest {

    @Resource
    private SysAclMapper mapper;

    @Test
    public void listAcl() {
        List<SysAcl> sysAcls = mapper.listAcl();
        System.out.println(sysAcls.size());
    }
}