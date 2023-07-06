package com.atguigu.crowd.constant;

/**
 * ClassName: CrowdConstant
 * Package: com.atguigu.crowd.util
 * Description:
 *
 * @Author peter
 * @Create 2023/5/17 13:44
 * @Version 1.0
 */
public class CrowdConstant {


    public static final String MASSAGE_LOGIN_FAILED = "登陆失败，请确认账号或密码是否正确！";
    public static final String MASSAGE_LOGIN_ACCT_ALREADY_IN_USE = "很抱歉，该账号已经被使用！";
    public static final String MASSAGE_ACCESS_FORBIDEN = "请登陆以后访问！";
    public static final String MESSAGE_STRING_INVALIDATE = "密码不合法，请不要传入空值！";
    public static final String MESSAGE_ACCESS_FORBIDEN = "请返回登陆页面，登陆账号！";
    public static final String MESSAGE_ACCESS_DENIED = "抱歉，您不能访问该资源！";
    public static final String MESSAGE_CODE_INVALID = "验证码不正确!";
    public static final String MESSAGE_CODE_NOT_EXISTS = "验证码已过期！请检查手机号是否正确或重新发送！";
    public static final String MESSAGE_HEADER_PIC_EMPTY = "头图不可为空!";
    public static final String MESSAGE_HEADER_PIC_UPLOAD_FAILED = "头图上传失败!";
    public static final String MESSAGE_DETAIL_PIC_EMPTY = "详情图片不可为空!";
    public static final String MESSAGE_DETAIL_PIC_UPLOAD_FAILED = "详情图片上传失败!";
    public static final String MESSAGE_TEMPLE_PROJECT_MISSING = "临时存储的Project对象丢失!";



    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String ATTR_NAME_MESSAGE = "message";
    public static final String ATTR_NAME_LOGIN_MEMBER = "loginMember";
    public static final String ATTR_NAME_TEMPLE_PROJECT = "tempProject";
    public static final String ATTR_NAME_PORTAL_DATA = "portal_data";

    public static final String REDIS_CODE_PREFIX = "REDIS_CODE_PREFIX_";
}
