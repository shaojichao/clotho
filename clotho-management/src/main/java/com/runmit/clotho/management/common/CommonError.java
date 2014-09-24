package com.runmit.clotho.management.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TianLiang
 */
@XmlRootElement(name = "CommonError")
public class CommonError extends BaseResponseObject {

    private String exception;

    public String getException() {
        return this.exception;
    }

    public void setException(String Exception) {
        this.exception = Exception;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(getClass().getSimpleName());
        s.append("[");
        s.append("path=").append(this.path);
        s.append(", ");
        s.append("status=").append(this.status);
        s.append(", ");
        s.append("httpStatusCode=").append(this.httpStatusCode);
        s.append(", ");
        s.append("message=").append(this.message);
        s.append(", ");
        s.append("exception=").append(this.exception);
        s.append("]");

        return s.toString();
    }
}
