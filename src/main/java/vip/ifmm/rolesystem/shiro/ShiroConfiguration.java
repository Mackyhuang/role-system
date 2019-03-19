package vip.ifmm.rolesystem.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import vip.ifmm.rolesystem.entity.SysAcl;
import vip.ifmm.rolesystem.service.AclService;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/11 22:39
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private AclService aclService;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
//        登陆成功页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
//        未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//        最核心的， 某某请求如何拦截
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        Shiro实现了注销代码，这里只要配置注销过滤器
        filterChainDefinitionMap.put("/logout", "logout");

//        静态资源
        filterChainDefinitionMap.put("/asserts/css/**","anon");
        filterChainDefinitionMap.put("/asserts/js/**","anon");
        filterChainDefinitionMap.put("/asserts/img/**","anon");
        filterChainDefinitionMap.put("/asserts/font-awesome/**","anon");
        filterChainDefinitionMap.put("/checklogin","anon");
//        添加权限拦截
        List<SysAcl> aclList = aclService.listAcl();
        for(SysAcl acl : aclList){
            if (!StringUtils.isEmpty(acl.getUrl())) {
                String permission = "perms[" + acl.getUrl()+ "]";
                filterChainDefinitionMap.put(acl.getUrl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(OwnRealm ownRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(ownRealm);
        return manager;
    }

    @Bean
    public OwnRealm authRealm(CredentialMatcher credentialMatcher){
        OwnRealm ownRealm = new OwnRealm();
        //缓存控制器
        ownRealm.setCacheManager(new MemoryConstrainedCacheManager());
        //密码匹配器
        ownRealm.setCredentialsMatcher(credentialMatcher);
        return ownRealm;
    }

    @Bean("CredentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式让Springboot 和 shiro 的关联
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 开启代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
