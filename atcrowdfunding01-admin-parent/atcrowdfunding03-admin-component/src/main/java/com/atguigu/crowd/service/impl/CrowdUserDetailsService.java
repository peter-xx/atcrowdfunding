package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mvc.config.SecurityAdmin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.service.AuthService;
import com.atguigu.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CrowdUserDetailsService
 * Package: com.atguigu.crowd.service.impl
 * Description:
 *
 * @Author peter
 * @Create 2023/6/27 14:16
 * @Version 1.0
 */
@Service
public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminService.getAdminByLoginAcct(username);
        List<Role> assignedRole = roleService.getAssignedRole(admin.getId());
        List<String> assignedAuthNameByAdminId = authService.getAssignedAuthNameByAdminId(admin.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : assignedRole){
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            authorities.add(authority);
        }
        for (String authName : assignedAuthNameByAdminId){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authName);
            authorities.add(authority);
        }

        return new SecurityAdmin(admin, authorities);
    }
}
