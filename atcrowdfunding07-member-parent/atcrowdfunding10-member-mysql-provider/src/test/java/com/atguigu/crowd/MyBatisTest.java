package com.atguigu.crowd;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;


/**
 * ClassName: com.atguigu.crowd.MyBatisTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author peter
 * @Create 2023/6/29 8:58
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Test
    public void testMapper(){
        MemberPO memberPO = new MemberPO(null, "test", "123456", "testname", "123456@qq.com", 0, 0, "testrealname", "null", 0);
        memberPOMapper.insert(memberPO);
    }

}
