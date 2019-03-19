package vip.ifmm.rolesystem.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.ifmm.rolesystem.entity.SysUser;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/12 19:10
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/checklogin")
    public String checkLogin(SysUser user, Map<String, Object> model, HttpSession session){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            model.put("msg", "用户名或密码不能为空");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        logger.info(user.getUsername() + "..." + user.getPassword());
        try {
            subject.login(token);
            session.setAttribute("username", user.getUsername());
            return "redirect:index";
        } catch (LockedAccountException e){
            token.clear();
            model.put("msg", "账户已经被冻结");
            return "login";
        } catch (AuthenticationException e){
            token.clear();
            model.put("msg", "用户名或密码不正确");
            logger.info("用户名或密码不正确");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            subject.logout();
        }
        session.removeAttribute("username");
        return "login";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping({"/index", "/"})
    public String index(){
        return "index";
    }
}
