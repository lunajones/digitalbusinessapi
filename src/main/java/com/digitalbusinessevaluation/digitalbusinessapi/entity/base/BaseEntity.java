package com.digitalbusinessevaluation.digitalbusinessapi.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public abstract class BaseEntity implements Cloneable {

	private transient String id;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@JsonIgnore
	public abstract String getRouteName();

	public boolean equalsValidationForUpdate(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
