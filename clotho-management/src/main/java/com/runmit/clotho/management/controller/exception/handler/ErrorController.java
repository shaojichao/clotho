/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runmit.clotho.management.controller.exception.handler;

import com.runmit.clotho.management.common.CommonError;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author guojinhai_2001
 */
@Controller
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    /**
     * We cannot specify the 'headers = "Accept=application/json"' as this
     * handler was routered in by a ModelAndView.
     *
     * @param path
     * @param status
     * @param httpStatusCode
     * @param message
     * @param exception
     * @param request
     * @return
     */
    @RequestMapping(value = "/error/errorHandler", method = {RequestMethod.GET, RequestMethod.POST}/*, headers = "Accept=application/json"*/)
    public //@ResponseBody
            CommonError responseWithError(
                    @RequestParam("path") String path,
                    @RequestParam("status") String status,
                    @RequestParam("httpStatusCode") int httpStatusCode,
                    @RequestParam("message") String message,
                    @RequestParam("exception") String exception,
                    HttpServletRequest request) {
                CommonError commonError = new CommonError();
                commonError.setPath(path);
                commonError.setStatus(status);
                commonError.setHttpStatusCode(HttpStatus.valueOf(httpStatusCode).value());
                commonError.setMessage(message);

                // only show exceptoin message in debug mode
                if (LOGGER.isDebugEnabled()) {
                    commonError.setException(exception);
                } else {
                    commonError.setException("");
                }

                LOGGER.info("Rest response erro: {}", commonError);
                return commonError;
            }

            /**
             * We cannot specify the 'headers = "Accept=application/json"' as
             * this handler was routered in by a ModelAndView.
             *
             * @param error
             * @return
             */
            @RequestMapping(value = "/error/errorHandler", method = RequestMethod.POST/*, headers = "Accept=application/json"*/)
            public //@ResponseBody
                    CommonError responseWithError(@RequestBody CommonError error) {
                LOGGER.info("Rest response erro: {}", error);
                return error;
            }
}
