package com.common.demo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-11-02
 */
public class TbMasPatient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "PT_ID", type = IdType.AUTO)
    private Long ptId;
    /**
     * 名称
     */
    @TableField("PT_Name")
    private String ptName;
    /**
     * 登录Id
     */
    @TableField("PT_LID")
    private String ptLoginid;


    @TableField("PT_PHONE")
    private String pTPhoto;


    /**
     * 性别
     */
    @TableField("PT_SEX")
    private String ptSex;
    /**
     * 出生年月日
     */
    @TableField("PT_BIRTHDATE")
    private Date ptBirthday;
    /**
     * 现住址
     */
    @TableField("PT_ADDR")
    private String ptAddress;
    /**
     * 开始日期
     */
    @TableField("PT_FROMDATE")
    private Date ptFromtime;
    /**
     * 密码
     */
    @TableField("PT_PASSWORD")
    private String ptPassword;
    /**
     * 电话号码
     */
    @TableField("PT_Phone")
    private String ptPhone;
    /**
     * 0;有效其他无效
     */
    @TableField("PT_Status")
    private String ptStatus;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date CreateTime;
    /**
     * 最后修改时间
     */
    @TableField("ModifyTime")
    private Date ModifyTime;
//    @TableField("IsDeleted")
//    private Boolean IsDeleted;


    public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getPtLoginid() {
        return ptLoginid;
    }

    public void setPtLoginid(String ptLoginid) {
        this.ptLoginid = ptLoginid;
    }

    public String getPtSex() {
        return ptSex;
    }

    public void setPtSex(String ptSex) {
        this.ptSex = ptSex;
    }

    public Date getPtBirthday() {
        return ptBirthday;
    }

    public void setPtBirthday(Date ptBirthday) {
        this.ptBirthday = ptBirthday;
    }

    public String getPtAddress() {
        return ptAddress;
    }

    public void setPtAddress(String ptAddress) {
        this.ptAddress = ptAddress;
    }

    public Date getPtFromtime() {
        return ptFromtime;
    }

    public void setPtFromtime(Date ptFromtime) {
        this.ptFromtime = ptFromtime;
    }

    public String getPtPassword() {
        return ptPassword;
    }

    public void setPtPassword(String ptPassword) {
        this.ptPassword = ptPassword;
    }

    public String getPtPhone() {
        return ptPhone;
    }

    public void setPtPhone(String ptPhone) {
        this.ptPhone = ptPhone;
    }

    public String getPtStatus() {
        return ptStatus;
    }

    public void setPtStatus(String ptStatus) {
        this.ptStatus = ptStatus;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Date getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(Date ModifyTime) {
        this.ModifyTime = ModifyTime;
    }

//    public Boolean getIsDeleted() {
//        return IsDeleted;
//    }
//
//    public void setIsDeleted(Boolean IsDeleted) {
//        this.IsDeleted = IsDeleted;
//    }


    public String getpTPhoto() {
        return pTPhoto;
    }

    public void setpTPhoto(String pTPhoto) {
        this.pTPhoto = pTPhoto;
    }

//    public Boolean getDeleted() {
//        return IsDeleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        IsDeleted = deleted;
//    }

    @Override
    public String toString() {
        return "TbMasPatient{" +
        ", ptId=" + ptId +
        ", ptName=" + ptName +
        ", ptLoginid=" + ptLoginid +
        ", ptSex=" + ptSex +
        ", ptBirthday=" + ptBirthday +
        ", ptAddress=" + ptAddress +
        ", ptFromtime=" + ptFromtime +
        ", ptPassword=" + ptPassword +
        ", ptPhone=" + ptPhone +
        ", ptStatus=" + ptStatus +
        ", CreateTime=" + CreateTime +
        ", ModifyTime=" + ModifyTime +
//        ", IsDeleted=" + IsDeleted +
        "}";
    }
}
