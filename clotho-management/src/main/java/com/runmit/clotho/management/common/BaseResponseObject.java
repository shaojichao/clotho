package com.runmit.clotho.management.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TianLiang
 */
@XmlRootElement(name = "responseMessage")
public class BaseResponseObject {

    protected String path;
    protected String status;
    protected int httpStatusCode;
    protected String message;
    protected Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
