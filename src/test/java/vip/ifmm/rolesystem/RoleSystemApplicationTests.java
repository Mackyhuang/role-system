package vip.ifmm.rolesystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import vip.ifmm.rolesystem.entity.SysRole;
import vip.ifmm.rolesystem.entity.SysUser;
import vip.ifmm.rolesystem.mapper.SysUserMapper;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan
@MapperScan(basePackages = {"vip.ifmm.rolesystem.mapper"})
public class RoleSystemApplicationTests {

    @Resource
    private SysUserMapper userMapper;

    @Test
    public void getUserByUsername() {
        SysUser macky = userMapper.getUserByUsername("macky");
        Set<SysRole> roles = macky.getRoles();
        for (SysRole role : roles){
            System.out.println(role.getAcls().size());
        }

    }
}

