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
public class TbBusSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="SS_ID", type= IdType.AUTO)
	private Integer ssId;
    /**
     * 内部ID
     */
	private String ssEid;
    /**
     * 登录ID
     */
	private String ssLid;
    /**
     * 登录名
     */
	private String ssEname;
    /**
     * 登录时间
     */
	private Date ssLogintime;
    /**
     * 访问时间
     */
	private Date ssAccesstime;
    /**
     * 退出时间
     */
	private Date ssLoginouttime;
    /**
     * 密码
     */
	private String ssLenid;
    /**
     * 创建时间
     */
	private Date createtime;


	public Integer getSsId() {
		return ssId;
	}

	public void setSsId(Integer ssId) {
		this.ssId = ssId;
	}

	public String getSsEid() {
		return ssEid;
	}

	public void setSsEid(String ssEid) {
		this.ssEid = ssEid;
	}

	public String getSsLid() {
		return ssLid;
	}

	public void setSsLid(String ssLid) {
		this.ssLid = ssLid;
	}

	public String getSsEname() {
		return ssEname;
	}

	public void setSsEname(String ssEname) {
		this.ssEname = ssEname;
	}

	public Date getSsLogintime() {
		return ssLogintime;
	}

	public void setSsLogintime(Date ssLogintime) {
		this.ssLogintime = ssLogintime;
	}

	public Date getSsAccesstime() {
		return ssAccesstime;
	}

	public void setSsAccesstime(Date ssAccesstime) {
		this.ssAccesstime = ssAccesstime;
	}

	public Date getSsLoginouttime() {
		return ssLoginouttime;
	}

	public void setSsLoginouttime(Date ssLoginouttime) {
		this.ssLoginouttime = ssLoginouttime;
	}

	public String getSsLenid() {
		return ssLenid;
	}

	public void setSsLenid(String ssLenid) {
		this.ssLenid = ssLenid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "TbBusSession{" +
			", ssId=" + ssId +
			", ssEid=" + ssEid +
			", ssLid=" + ssLid +
			", ssEname=" + ssEname +
			", ssLogintime=" + ssLogintime +
			", ssAccesstime=" + ssAccesstime +
			", ssLoginouttime=" + ssLoginouttime +
			", ssLenid=" + ssLenid +
			", createtime=" + createtime +
			"}";
	}
}
