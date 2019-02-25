package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class City extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String num;


	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
}
