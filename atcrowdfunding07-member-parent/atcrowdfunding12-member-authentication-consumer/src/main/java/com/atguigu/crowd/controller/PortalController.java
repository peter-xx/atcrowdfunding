package com.atguigu.crowd.controller;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.vo.PortalTypeVO;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClassName: PortalController
 * Package: com.atguigu.crowd.controller
 * Description:
 *
 * @Author peter
 * @Create 2023/6/29 14:54
 * @Version 1.0
 */
@Controller
public class PortalController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;


    @RequestMapping("/")
    public String showPortalPage(ModelMap modelMap){

        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            List<PortalTypeVO> portalTypeVOList = resultEntity.getData();
//            portalTypeVOList = null;
//            for (PortalTypeVO portalTypeVO : portalTypeVOList){
//                portalTypeVO.setPortalProjectVOList(null);
//            }
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, portalTypeVOList);
        }

        return "portal";

    }

}
