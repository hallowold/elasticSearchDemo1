package com.example.demo.security.service;

import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.dao.SysRoleRightDao;
import com.example.demo.security.entity.SysRoleRight;
import com.example.demo.security.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * @author liuqitian
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysRoleRightDao sysRoleRightDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行,init()方法之前执行。
     *  一定要加上@PostConstruct注解
     */
    @PostConstruct
    private void loadResourceDefine() {
        // 在Web服务器启动时，提取系统中的所有权限。
        List<SysRole> sysRoles =sysRoleDao.findAll();
        //应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
        resourceMap = new HashMap<>();
        for (SysRole sysRole : sysRoles) {
            ConfigAttribute ca = new SecurityConfig(sysRole.getName());
            List<String> urlList = new ArrayList<String>();
            List<SysRoleRight>  sysRoleRights = sysRoleRightDao.findByRoleId(sysRole.getId());
            if(sysRoleRights != null && sysRoleRights.size() > 0) {
                //这里一定要注意，因为我们使用restful风格接口，所以要将请求类型拼在url后面。
                // 统一规定使用_进行拼接，方法大写拼接到最后面，在识别时截取最后一个_后面的字段即可，不需要限制路径中_字符的使用
                sysRoleRights.forEach(sysRoleRight ->
                        urlList.add(sysRoleRight.getsRight().getRightUrl() + "_" + sysRoleRight.getsRight().getMethodType()));
            }
            for (String url : urlList) {
                //判断资源文件和权限的对应关系，
                //  如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    /**
     * 根据URL和请求类型(GET,POST,DELETE等)，找到相关的权限配置。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        //获取request对象，我们需要使用它来判断请求类型
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            //这里注意，因为我们使用restful风格接口，所以在生成资源时候将请求类型拼在url后面，故这里取到的resURL是拼接后的结果
            // 统一规定使用_进行拼接，方法大写拼接到最后面，在识别时截取最后一个_后面的字段即可，不需要限制路径中_字符的使用
            String resURL = ite.next();
            String matchUrl = resURL.substring(0,resURL.indexOf("_"));
            String matchType = resURL.substring(resURL.indexOf("_") + 1);
            RequestMatcher requestMatcher = new AntPathRequestMatcher(matchUrl);
            //特殊判断一下ALL，匹配所有类型
            if(requestMatcher.matches(filterInvocation.getHttpRequest()) &&
                    (matchType.equalsIgnoreCase("ALL") || matchType.equals(request.getMethod()))) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
