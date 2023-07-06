package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: DetailProjectVO
 * Package: com.atguigu.crowd.entity.vo
 * Description:
 *
 * @Author peter
 * @Create 2023/7/4 11:07
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO {

    private Integer projectId;
    private String projectName;
    private String projectDesc;
    private Integer followerCount;
    private Integer status;
    private String statusText;
    private Integer money;
    private Integer supportMoney;
    private Integer percentage;
    private String deployDate;
    private Integer day;
    private Integer lastDay;
    private Integer supporterCount;
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<DetailReturnVO> detailReturnVOList;

}
