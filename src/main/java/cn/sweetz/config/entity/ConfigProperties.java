package cn.sweetz.config.entity;

import java.io.Serializable;
import java.util.Date;

public class ConfigProperties implements Serializable {
	private static final long serialVersionUID = -5131717832428818174L;
	private Integer id;
	private String pkey;
	private String pvalue;
	private String beforeValue;
	private String application;
	private String profile;
	private String label;
	private String isPublish;
	private Date createTime;
	private Date updateTime;
	private String isDeleted;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	public String getBeforeValue() {
		return this.beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIsPublish() {
		return this.isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String toString() {
		return "ConfigProperties [id=" + this.id + ", pkey=" + this.pkey + ", pvalue=" + this.pvalue + ", beforeValue="
				+ this.beforeValue + ", application=" + this.application + ", profile=" + this.profile + ", label="
				+ this.label + ", isPublish=" + this.isPublish + ", createTime=" + this.createTime + ", updateTime="
				+ this.updateTime + ", isDeleted=" + this.isDeleted + "]";
	}
}