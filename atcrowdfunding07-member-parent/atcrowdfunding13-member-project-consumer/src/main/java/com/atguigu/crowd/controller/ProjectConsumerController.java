package com.atguigu.crowd.controller;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.config.OSSProperties;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.vo.*;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ProjectConsumerController
 * Package: com.atguigu.crowd.controller
 * Description:
 *
 * @Author peter
 * @Create 2023/7/3 14:36
 * @Version 1.0
 */
@Controller
public class ProjectConsumerController {

    @Autowired
    private OSSProperties ossProperties;
    @Autowired
    private MySQLRemoteService mySQLRemoteService;


    @RequestMapping("get/project/detail/{projectId}")
    public String getProjectDetail(@PathVariable("projectId") Integer projectId, ModelMap modelMap){

        ResultEntity<DetailProjectVO> detailProjectVOResultEntity = mySQLRemoteService.getDetailProjectRemote(projectId);
        if (ResultEntity.SUCCESS.equals(detailProjectVOResultEntity.getResult())){
            DetailProjectVO detailProjectVO = detailProjectVOResultEntity.getData();
            modelMap.addAttribute("detailProjectVO",detailProjectVO);
        }
        return "project-show-detail";

    }

    @RequestMapping("/create/confirm")
    public String saveConfirm(MemberConfirmInfoVO memberConfirmInfoVO, HttpSession session, ModelMap modelMap){

        ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
        if (projectVO == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        MemberLoginVO loginMember = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = loginMember.getId();
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO, memberId);
        if (ResultEntity.FAILED.equals(saveResultEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveResultEntity.getMessage());
            return "project-confirm";
        }

        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
        return "redirect:http://localhost/project/create/success";
    }

    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session){
        try {
            ProjectVO projectVo = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
            if (projectVo == null) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }
            List<ReturnVO> returnVOList = projectVo.getReturnVOList();
            if (returnVOList == null || returnVOList.size() == 0){
                returnVOList = new ArrayList<ReturnVO>();
                projectVo.setReturnVOList(returnVOList);
            }
            returnVOList.add(returnVO);
            session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVo);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(@RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
        ResultEntity<String> uploadReturnPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename()
        );
        return uploadReturnPicResultEntity;
    }

    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(ProjectVO projectVO,
                                       MultipartFile headerPicture,
                                       List<MultipartFile> detailPictureList,
                                       HttpSession session,
                                       ModelMap modelMap) throws IOException {

        //1. 上传头图
        boolean isEmpty = headerPicture.isEmpty();
        if(isEmpty) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "project-launch";
        }
        ResultEntity<String> uploadHeadPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());
        String uploadHeadPicResult = uploadHeadPicResultEntity.getResult();
        if (ResultEntity.SUCCESS.equals(uploadHeadPicResult)){
            String headerPicturePath = uploadHeadPicResultEntity.getData();
            projectVO.setHeaderPicturePath(headerPicturePath);
        }else {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
            return "project-launch";
        }

        //2. 上传详情图片
        ArrayList<String> detailPicturePathList = new ArrayList<>();
        if (detailPictureList.size() == 0 || detailPictureList == null){
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
            return "project-launch";
        }
        for (MultipartFile detailPicture : detailPictureList){
            if (detailPicture.isEmpty()){
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
                return "project-launch";
            }
            ResultEntity<String> uploadDetailPicResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    headerPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    headerPicture.getOriginalFilename());
            String uploadDetailPicResult = uploadDetailPicResultEntity.getResult();
            if(ResultEntity.SUCCESS.equals(uploadDetailPicResult)){
                detailPicturePathList.add(uploadDetailPicResultEntity.getData());
            }else {
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
                return "project-launch";
            }
        }
        projectVO.setDetailPicturePathList(detailPicturePathList);

        //3. 后续操作
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
        return "redirect:http://localhost/project/return/info/page";
    }

}
