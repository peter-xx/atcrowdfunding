package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: PortalTypeVO
 * Package: com.atguigu.crowd.entity.vo
 * Description:
 *
 * @Author peter
 * @Create 2023/7/4 9:45
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalTypeVO {

    private  Integer id;
    private String name;
    private String remark;

    private List<PortalProjectVO> portalProjectVOList;

}
