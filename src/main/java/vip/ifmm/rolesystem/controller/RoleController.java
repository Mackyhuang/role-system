package vip.ifmm.rolesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vip.ifmm.rolesystem.entity.SysAcl;
import vip.ifmm.rolesystem.entity.SysRole;
import vip.ifmm.rolesystem.entity.SysUser;
import vip.ifmm.rolesystem.service.AclService;
import vip.ifmm.rolesystem.service.RoleService;
import vip.ifmm.rolesystem.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/13 20:16
 */
@Controller
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private AclService aclService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     * @param map
     * @return
     */
    @GetMapping("/users")
    public String listUser(Map<String, Object> map){
        List<SysUser> userList = userService.listUser();
        map.put("users", userList);
        return "role/userList";
    }

    /**
     * 权限列表
     * @param map
     * @return
     */
    @GetMapping("/acls")
    public String listAcl(Map<String, Object> map){
        List<SysAcl> aclList = aclService.listAclDetail();
        map.put("acls", aclList);
        return "role/aclList";
    }

    /**
     * 角色列表
     * @param map
     * @return
     */
    @GetMapping("/roles")
    public String listRole(Map<String, Object> map){
        List<SysRole> roleList = roleService.listRole();
        map.put("roles", roleList);
        return "role/roleList";
    }

    /**
     * 添加用户
     * @param user
     * @param map
     * @return
     */
    @PostMapping("/user")
    public String saveUser(SysUser user, Map<String, Object> map){
        String msg = userService.saveUser(user);
        map.put("msg", msg);
        return "redirect:/users";
    }

    /**
     * 跳转用户添加页面
     * @param map
     * @return
     */
    @GetMapping("/user")
    public String toSaveUserPage(Map<String, Object> map){
        //TODO 部门列表
        return "role/userAdd";
    }

    /**
     * 跳转用户更新页面
     * @param uId
     * @param map
     * @return
     */
    @GetMapping("/user/{uId}")
    public String toUpdateUserPage(@PathVariable("uId") String uId, Map<String, Object> map){
        SysUser user = userService.getUser(uId);
        System.out.println(user.getuId());
        map.put("user", user);
        return "role/userEdit";
    }

    /**
     * 用户更新
     * @param user
     * @param map
     * @return
     */
    @PutMapping("/user")
    public String updateUser(SysUser user, Map<String, Object> map){
        String msg = userService.updateUser(user);
        map.put("msg", msg);
        return "redirect:/users";
    }

    /**
     * 删除用户
     * @param uId
     * @param map
     * @return
     */
    @DeleteMapping("/user/{uId}")
    public String deleteUser(@PathVariable("uId") String uId, Map<String, Object> map){
        SysUser user = new SysUser();
        user.setuId(uId);
        String msg = userService.removeUser(user);
        map.put("msg", msg);
        return "redirect:/users";
    }



    /**
     * 添加权限
     * @param acl
     * @param map
     * @return
     */
    @PostMapping("/acl")
    public String saveAcl(SysAcl acl, Map<String, Object> map){
        String msg = aclService.saveAcl(acl);
        map.put("msg", msg);
        return "redirect:/acls";
    }

    /**
     * 跳转权限添加页面
     * @param map
     * @return
     */
    @GetMapping("/acl")
    public String toSaveAclPage(Map<String, Object> map){
        //TODO 部门列表
        return "role/aclAdd";
    }

    /**
     * 跳转用户更新页面
     * @param aId
     * @param map
     * @return
     */
    @GetMapping("/acl/{aId}")
    public String toUpdateAclPage(@PathVariable("aId") String aId, Map<String, Object> map){
        SysAcl acl = aclService.getAcl(aId);
        System.out.println(acl.getaId());
        map.put("acl", acl);
        return "role/aclEdit";
    }

    /**
     * 用户更新
     * @param acl
     * @param map
     * @return
     */
    @PutMapping("/acl")
    public String updateAcl(SysAcl acl, Model map){
        System.out.println("update!!!!!!____________");
        String msg = aclService.updateAcl(acl);
        map.addAttribute("msg", msg);
        return "redirect:/acls";
    }

    /**
     * 删除权限
     * @param aId
     * @param map
     * @return
     */
    @DeleteMapping("/acl/{aId}")
    public String deleteAcl(@PathVariable("aId") String aId, Map<String, Object> map){
        SysAcl acl = new SysAcl();
        acl.setaId(aId);
        String msg = aclService.removeAcl(acl);
        map.put("msg", msg);
        return "redirect:/acls";
    }

}
