package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * ClassName: AuthService
 * Package: com.atguigu.crowd.service
 * Description:
 *
 * @Author peter
 * @Create 2023/6/25 17:09
 * @Version 1.0
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelathinship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
