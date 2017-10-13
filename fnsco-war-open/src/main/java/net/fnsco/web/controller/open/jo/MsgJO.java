package net.fnsco.web.controller.open.jo;

import java.util.Date;

import net.fnsco.core.base.JO;

public class MsgJO extends JO {
    private Integer id;

    private String  msgSubject;

    private String  imageUrl;

    private String  detailUrl;

    private String  msgSubTitle;

    private String  modifyTime;

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */

    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * msgSubject
     *
     * @return  the msgSubject
     * @since   CodingExample Ver 1.0
    */

    public String getMsgSubject() {
        return msgSubject;
    }

    /**
     * msgSubject
     *
     * @param   msgSubject    the msgSubject to set
     * @since   CodingExample Ver 1.0
     */

    public void setMsgSubject(String msgSubject) {
        this.msgSubject = msgSubject;
    }

    /**
     * imageUrl
     *
     * @return  the imageUrl
     * @since   CodingExample Ver 1.0
    */

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * imageUrl
     *
     * @param   imageUrl    the imageUrl to set
     * @since   CodingExample Ver 1.0
     */

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * detailUrl
     *
     * @return  the detailUrl
     * @since   CodingExample Ver 1.0
    */

    public String getDetailUrl() {
        return detailUrl;
    }

    /**
     * detailUrl
     *
     * @param   detailUrl    the detailUrl to set
     * @since   CodingExample Ver 1.0
     */

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    /**
     * msgSubTitle
     *
     * @return  the msgSubTitle
     * @since   CodingExample Ver 1.0
    */

    public String getMsgSubTitle() {
        return msgSubTitle;
    }

    /**
     * msgSubTitle
     *
     * @param   msgSubTitle    the msgSubTitle to set
     * @since   CodingExample Ver 1.0
     */

    public void setMsgSubTitle(String msgSubTitle) {
        this.msgSubTitle = msgSubTitle;
    }

    /**
     * modifyTime
     *
     * @return  the modifyTime
     * @since   CodingExample Ver 1.0
    */

    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * modifyTime
     *
     * @param   modifyTime    the modifyTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

}
