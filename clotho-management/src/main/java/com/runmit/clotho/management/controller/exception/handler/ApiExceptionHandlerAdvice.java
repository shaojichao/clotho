/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runmit.clotho.management.controller.exception.handler;

import com.runmit.clotho.management.common.CommonError;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 *
 * @author guojinhai_2001
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {

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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleBadRequest(NoSuchRequestHandlingMethodException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The page not found " + path, ex);

        CommonError error = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "The page not found " + path, ex);
        return error;
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The media type is not acceptable when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.NOT_ACCEPTABLE.toString(), HttpStatus.NOT_ACCEPTABLE, "The media type is not acceptable when access " + path, ex);
        return error;
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleNotAcceptable(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("The media type is upsupported when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, "The media type is upsupported when access " + path, ex);
        return error;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleMessageReadException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't read from stream when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "Can't read from stream when access " + path, ex);
        return error;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleRequestParameterMissing(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.error("The required http parameter is missing when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "The required http parameter is missing when access " + path, ex);
        return error;
    }

    @ExceptionHandler({HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleMessageWriteException(HttpMessageNotWritableException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't write to stream when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Can't write to stream when access " + path, ex);
        return error;
    }

    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleTypeMismatchException(TypeMismatchException ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Type is mismatch when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Type is mismatch when access " + path, ex);
        return error;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonError handleUncaughtException(Exception ex, HttpServletRequest request) {
        String path = request.getPathInfo();

        logger.warn("Can't accomplish the request because an unexpected error when access " + path, ex);
        CommonError error = composeModelAndView(path, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Can't accomplish the request because an unexpected error when access " + path, ex);
        return error;
    }

    private CommonError composeModelAndView(String path, String status, HttpStatus httpStatusCode, String message, Exception ex) {
        CommonError error = new CommonError();
        error.setPath(path);
        error.setHttpStatusCode(httpStatusCode.value());
        error.setStatus(status);
        error.setException(ex.getMessage());
        error.setMessage(message);
        return error;
    }

    private String getExtension(String path) {
        if ((StringUtils.isNotEmpty(path))
                && (path.indexOf(".") != -1)) {
            return path.substring(path.indexOf("."));
        }

        return "";
    }
}
