package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CrowdExceptionResolver
 * Package: com.atguigu.crowd.mvc.config
 * Description:
 *
 * @Author peter
 * @Create 2023/5/17 11:17
 * @Version 1.0
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception, HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";
        return commonException(viewName, exception, request, response);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView resolveAccessDeniedException(
            AccessDeniedException exception, HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";
        return commonException(viewName, exception, request, response);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView nullException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String viewName = "admin-login";
        return commonException(viewName, exception, request, response);

    }

    private ModelAndView commonException(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean judgeRequestType = CrowdUtil.JudgeRequestType(request);

        if (judgeRequestType){
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            return null;
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }

}
