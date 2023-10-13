package com.springpj.heroescontentcreator.model.user;

public enum AccountStatus {
	ACTIVE,
	LOCKED,
	EXPIRED,
	DELETED;
	
	public boolean isActive() {
		return this.equals(ACTIVE);
	}
	
	public boolean isExpired() {
		return this.equals(EXPIRED);
	}
	
	public boolean isLocked() {
		return this.equals(LOCKED);
	}
	
	public boolean isDeleted() {
		return this.equals(DELETED);
	}
}
