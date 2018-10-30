package com.common.demo;

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
public class TbMasDoctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="DT_ID", type= IdType.AUTO)
	private Integer dtId;
    /**
     * 名称
     */
	private String dtName;
    /**
     * 分类
     */
	private String dtSort;
    /**
     * 登录Id
     */
	private String dtWorkid;
    /**
     * 性别
     */
	private String dtSex;
    /**
     * 出生年月日
     */
	private Date dtBirthday;
    /**
     * 现住址
     */
	private String dtAddr;
    /**
     * 开始日期
     */
	private Date dtFromdate;
    /**
     * 密码
     */
	private String dtPassword;
    /**
     * 电话号码
     */
	private String dtPhone;
	private String dtDeptcode;
	private String dtHospitalname;
	private String dtSpec;
    /**
     * 0;有效其他无效
     */
	private String dtStatus;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 最后修改时间
     */
	private Date modifytime;


	public Integer getDtId() {
		return dtId;
	}

	public void setDtId(Integer dtId) {
		this.dtId = dtId;
	}

	public String getDtName() {
		return dtName;
	}

	public void setDtName(String dtName) {
		this.dtName = dtName;
	}

	public String getDtSort() {
		return dtSort;
	}

	public void setDtSort(String dtSort) {
		this.dtSort = dtSort;
	}

	public String getDtWorkid() {
		return dtWorkid;
	}

	public void setDtWorkid(String dtWorkid) {
		this.dtWorkid = dtWorkid;
	}

	public String getDtSex() {
		return dtSex;
	}

	public void setDtSex(String dtSex) {
		this.dtSex = dtSex;
	}

	public Date getDtBirthday() {
		return dtBirthday;
	}

	public void setDtBirthday(Date dtBirthday) {
		this.dtBirthday = dtBirthday;
	}

	public String getDtAddr() {
		return dtAddr;
	}

	public void setDtAddr(String dtAddr) {
		this.dtAddr = dtAddr;
	}

	public Date getDtFromdate() {
		return dtFromdate;
	}

	public void setDtFromdate(Date dtFromdate) {
		this.dtFromdate = dtFromdate;
	}

	public String getDtPassword() {
		return dtPassword;
	}

	public void setDtPassword(String dtPassword) {
		this.dtPassword = dtPassword;
	}

	public String getDtPhone() {
		return dtPhone;
	}

	public void setDtPhone(String dtPhone) {
		this.dtPhone = dtPhone;
	}

	public String getDtDeptcode() {
		return dtDeptcode;
	}

	public void setDtDeptcode(String dtDeptcode) {
		this.dtDeptcode = dtDeptcode;
	}

	public String getDtHospitalname() {
		return dtHospitalname;
	}

	public void setDtHospitalname(String dtHospitalname) {
		this.dtHospitalname = dtHospitalname;
	}

	public String getDtSpec() {
		return dtSpec;
	}

	public void setDtSpec(String dtSpec) {
		this.dtSpec = dtSpec;
	}

	public String getDtStatus() {
		return dtStatus;
	}

	public void setDtStatus(String dtStatus) {
		this.dtStatus = dtStatus;
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
		return "TbMasDoctor{" +
			", dtId=" + dtId +
			", dtName=" + dtName +
			", dtSort=" + dtSort +
			", dtWorkid=" + dtWorkid +
			", dtSex=" + dtSex +
			", dtBirthday=" + dtBirthday +
			", dtAddr=" + dtAddr +
			", dtFromdate=" + dtFromdate +
			", dtPassword=" + dtPassword +
			", dtPhone=" + dtPhone +
			", dtDeptcode=" + dtDeptcode +
			", dtHospitalname=" + dtHospitalname +
			", dtSpec=" + dtSpec +
			", dtStatus=" + dtStatus +
			", createtime=" + createtime +
			", modifytime=" + modifytime +
			"}";
	}
}
