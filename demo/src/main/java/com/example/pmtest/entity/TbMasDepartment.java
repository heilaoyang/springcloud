package com.example.pmtest.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-10-29
 */
public class TbMasDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String dpCode;
    /**
     * 名称
     */
	private String dpName;
    /**
     * 父部门编码
     */
	private String dpParentcode;
    /**
     * 级别
     */
	private Integer dpLevel;
    /**
     * 0;有效 其他无效
     */
	private String dpDelflag;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 最后修改时间
     */
	private Date modifytime;


	public String getDpCode() {
		return dpCode;
	}

	public void setDpCode(String dpCode) {
		this.dpCode = dpCode;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public String getDpParentcode() {
		return dpParentcode;
	}

	public void setDpParentcode(String dpParentcode) {
		this.dpParentcode = dpParentcode;
	}

	public Integer getDpLevel() {
		return dpLevel;
	}

	public void setDpLevel(Integer dpLevel) {
		this.dpLevel = dpLevel;
	}

	public String getDpDelflag() {
		return dpDelflag;
	}

	public void setDpDelflag(String dpDelflag) {
		this.dpDelflag = dpDelflag;
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
		return "TbMasDepartment{" +
			", dpCode=" + dpCode +
			", dpName=" + dpName +
			", dpParentcode=" + dpParentcode +
			", dpLevel=" + dpLevel +
			", dpDelflag=" + dpDelflag +
			", createtime=" + createtime +
			", modifytime=" + modifytime +
			"}";
	}
}
