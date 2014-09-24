package com.runmit.clotho.rest.common;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TianLiang
 */
@Data
@XmlRootElement(name = "CommonError")
public class CommonError {

    private String path;
    private String status;
    private int httpStatusCode;
    private String message;
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	private Boolean success;
    private String exception;

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
