package com.github.mrcaoyc.office.online.factory.constants;

import com.github.mrcaoyc.common.errormessage.BaseErrorMessage;

/**
 * @author CaoYongCheng
 */
public enum OfficeOnlineErrorMessage implements BaseErrorMessage {
    NONSUPPORT(101, "暂不支持该操作！");
    private int code;
    private String message;

    OfficeOnlineErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
