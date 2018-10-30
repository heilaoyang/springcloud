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
public class TbMasGeneralcode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String gcKeycode;
    /**
     * 分类
     */
	private String gcCata;
    /**
     * 分类名
     */
	private String gcCataname;
    /**
     * 编码
     */
	private String gcCode;
    /**
     * 名称
     */
	private String gcName;
    /**
     * 排序
     */
	private String gcOrderby;
    /**
     * 1:启用；0：停用
     */
	private String gcDeleteflag;
    /**
     * 备注
     */
	private String gcMemo;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 最后修改时间
     */
	private Date modifytime;


	public String getGcKeycode() {
		return gcKeycode;
	}

	public void setGcKeycode(String gcKeycode) {
		this.gcKeycode = gcKeycode;
	}

	public String getGcCata() {
		return gcCata;
	}

	public void setGcCata(String gcCata) {
		this.gcCata = gcCata;
	}

	public String getGcCataname() {
		return gcCataname;
	}

	public void setGcCataname(String gcCataname) {
		this.gcCataname = gcCataname;
	}

	public String getGcCode() {
		return gcCode;
	}

	public void setGcCode(String gcCode) {
		this.gcCode = gcCode;
	}

	public String getGcName() {
		return gcName;
	}

	public void setGcName(String gcName) {
		this.gcName = gcName;
	}

	public String getGcOrderby() {
		return gcOrderby;
	}

	public void setGcOrderby(String gcOrderby) {
		this.gcOrderby = gcOrderby;
	}

	public String getGcDeleteflag() {
		return gcDeleteflag;
	}

	public void setGcDeleteflag(String gcDeleteflag) {
		this.gcDeleteflag = gcDeleteflag;
	}

	public String getGcMemo() {
		return gcMemo;
	}

	public void setGcMemo(String gcMemo) {
		this.gcMemo = gcMemo;
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
		return "TbMasGeneralcode{" +
			", gcKeycode=" + gcKeycode +
			", gcCata=" + gcCata +
			", gcCataname=" + gcCataname +
			", gcCode=" + gcCode +
			", gcName=" + gcName +
			", gcOrderby=" + gcOrderby +
			", gcDeleteflag=" + gcDeleteflag +
			", gcMemo=" + gcMemo +
			", createtime=" + createtime +
			", modifytime=" + modifytime +
			"}";
	}
}
