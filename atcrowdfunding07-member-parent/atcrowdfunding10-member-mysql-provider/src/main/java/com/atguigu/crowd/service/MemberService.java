package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.po.MemberPO;

/**
 * ClassName: MemberService
 * Package: com.atguigu.crowd.service
 * Description:
 *
 * @Author peter
 * @Create 2023/6/29 9:25
 * @Version 1.0
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);


    void saveMember(MemberPO memberPO);
}
