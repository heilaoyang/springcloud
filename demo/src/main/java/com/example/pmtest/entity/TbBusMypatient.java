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
public class TbBusMypatient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="MP_ID", type= IdType.AUTO)
	private Long mpId;
    /**
     * 病人ID
     */
	private Long mpPtid;
    /**
     * 医生ID
     */
	private Integer mpDtid;
	private String mpStatus;
	private Date mpAprtime;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 最后修改时间
     */
	private Date modifytime;


	public Long getMpId() {
		return mpId;
	}

	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}

	public Long getMpPtid() {
		return mpPtid;
	}

	public void setMpPtid(Long mpPtid) {
		this.mpPtid = mpPtid;
	}

	public Integer getMpDtid() {
		return mpDtid;
	}

	public void setMpDtid(Integer mpDtid) {
		this.mpDtid = mpDtid;
	}

	public String getMpStatus() {
		return mpStatus;
	}

	public void setMpStatus(String mpStatus) {
		this.mpStatus = mpStatus;
	}

	public Date getMpAprtime() {
		return mpAprtime;
	}

	public void setMpAprtime(Date mpAprtime) {
		this.mpAprtime = mpAprtime;
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
		return "TbBusMypatient{" +
			", mpId=" + mpId +
			", mpPtid=" + mpPtid +
			", mpDtid=" + mpDtid +
			", mpStatus=" + mpStatus +
			", mpAprtime=" + mpAprtime +
			", createtime=" + createtime +
			", modifytime=" + modifytime +
			"}";
	}
}
