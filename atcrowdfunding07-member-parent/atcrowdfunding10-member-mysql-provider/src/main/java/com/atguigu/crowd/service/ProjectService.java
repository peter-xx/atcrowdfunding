package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.vo.DetailProjectVO;
import com.atguigu.crowd.entity.vo.PortalTypeVO;
import com.atguigu.crowd.entity.vo.ProjectVO;

import java.util.List;

/**
 * ClassName: ProjectService
 * Package: com.atguigu.crowd.service
 * Description:
 *
 * @Author peter
 * @Create 2023/7/3 13:50
 * @Version 1.0
 */
public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}
