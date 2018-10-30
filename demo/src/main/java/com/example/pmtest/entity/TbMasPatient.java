package com.example.pmtest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-10-29
 */
public class TbMasPatient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="PT_ID", type= IdType.AUTO)
	private Long ptId;
    /**
     * 名称
     */
	private String ptName;
    /**
     * 登录Id
     */
	private String ptLid;
    /**
     * 性别
     */
	private String ptSex;
    /**
     * 出生年月日
     */
	private Date ptBirthdate;
    /**
     * 现住址
     */
	private String ptAddr;
    /**
     * 开始日期
     */
	private Date ptFromdate;
    /**
     * 密码
     */
	private String ptPassword;
    /**
     * 电话号码
     */
	private String ptPhone;
    /**
     * 0;有效其他无效
     */
	private String ptStatus;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 最后修改时间
     */
	private Date modifytime;


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

	public String getPtLid() {
		return ptLid;
	}

	public void setPtLid(String ptLid) {
		this.ptLid = ptLid;
	}

	public String getPtSex() {
		return ptSex;
	}

	public void setPtSex(String ptSex) {
		this.ptSex = ptSex;
	}

	public Date getPtBirthdate() {
		return ptBirthdate;
	}

	public void setPtBirthdate(Date ptBirthdate) {
		this.ptBirthdate = ptBirthdate;
	}

	public String getPtAddr() {
		return ptAddr;
	}

	public void setPtAddr(String ptAddr) {
		this.ptAddr = ptAddr;
	}

	public Date getPtFromdate() {
		return ptFromdate;
	}

	public void setPtFromdate(Date ptFromdate) {
		this.ptFromdate = ptFromdate;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "TbMasPatient{" +
			", ptId=" + ptId +
			", ptName=" + ptName +
			", ptLid=" + ptLid +
			", ptSex=" + ptSex +
			", ptBirthdate=" + ptBirthdate +
			", ptAddr=" + ptAddr +
			", ptFromdate=" + ptFromdate +
			", ptPassword=" + ptPassword +
			", ptPhone=" + ptPhone +
			", ptStatus=" + ptStatus +
			", createtime=" + createtime +
			", modifytime=" + modifytime +
			"}";
	}
}
