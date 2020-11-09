package com.taisf.services.device.logic.aaa.command.base;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public class LinkApiException extends RuntimeException {

    public LinkApiException() {
    }

    public LinkApiException(String message) {
        super(message);
    }

    public LinkApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
