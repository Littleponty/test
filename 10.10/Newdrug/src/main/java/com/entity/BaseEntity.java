package com.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ON_SAVE_METHOD_NAME = "onSave";// "保存"方法名称
	public static final String ON_UPDATE_METHOD_NAME = "onUpdate";// "更新"方法名称
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"

	protected Long id;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public void onSave() {
	}

	@Transient
	public void onUpdate() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null || other.getId() == null) {
			return false;
		} else {
			return (id.equals(other.getId()));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}