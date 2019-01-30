package com.example.demo.model;

/**
 * Created by Administrator on 2017/12/13.
 */


//@Data
public class Message {

    private String messageCode;

    private String messageStatus;

    private String cause;
    private String img;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * 获取 #{bare_field_comment}
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置 #{bare_field_comment}
     * @param img #{bare_field_comment}
     */
    public void setImg(String img) {
        this.img = img;
    }
    

}
