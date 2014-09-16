/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runmit.uc.management.controller.exception.handler;

import com.runmit.uc.management.common.CommonError;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 *
 * @author guojinhai_2001
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private HttpServletRequest request;

    @Autowired
    @Required
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @ExceptionHandler({NoSuchRequestHandlingMethodException.class})
    public ModelAndView handleBadRequest(NoSuchRequestHandlingMethodException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The page not found " + path, ex);

        ModelAndView mav = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "The page not found " + path, ex);
        return mav;
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ModelAndView handleNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The media type is not acceptable when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.NOT_ACCEPTABLE.toString(), HttpStatus.NOT_ACCEPTABLE, "The media type is not acceptable when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ModelAndView handleNotAcceptable(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The media type is upsupported when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, "The media type is upsupported when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ModelAndView handleMessageReadException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't read from stream when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "Can't read from stream when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ModelAndView handleRequestParameterMissing(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.error("The required http parameter is missing when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "The required http parameter is missing when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({HttpMessageNotWritableException.class})
    public ModelAndView handleMessageWriteException(HttpMessageNotWritableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't write to stream when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Can't write to stream when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ModelAndView handleTypeMismatchException(TypeMismatchException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Type is mismatch when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Type is mismatch when access " + path, ex);
        return mav;
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleUncaughtException(Exception ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't accomplish the request because an unexpected error when access " + path, ex);
        ModelAndView mav = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Can't accomplish the request because an unexpected error when access " + path, ex);
        return mav;
    }

    private ModelAndView composeModelAndView(String path, String status, HttpStatus httpStatusCode, String message, Exception ex) {
        String extension = getExtension(path);
        String method = this.request.getMethod();
        ModelAndView mv = new ModelAndView();
        if ((HttpMethod.POST.toString().equals(method)) || (HttpMethod.PUT.toString().equals(method))) {
            CommonError error = new CommonError();
            error.setPath(path);
            error.setHttpStatusCode(httpStatusCode.value());
            error.setStatus(status);
            error.setException(ex.getMessage());
            error.setMessage(message);
            mv.addObject(error);
        } else {
            mv.setView(new InternalResourceView("/api/error/errorHandler" + extension + "?path=" + path + "&status=" + status + "&httpStatusCode=" + httpStatusCode.value() + "&message=" + message + "&exception=" + ex));
        }

        return mv;
    }
    private String getExtension(String path) {
        if ((StringUtils.isNotEmpty(path))
                && (path.indexOf(".") != -1)) {
            return path.substring(path.indexOf("."));
        }

        return "";
    }
}
