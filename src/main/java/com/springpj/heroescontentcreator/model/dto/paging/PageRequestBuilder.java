package com.springpj.heroescontentcreator.model.dto.paging;

public class PageRequestBuilder {

	private int page;
	private int pageSize;
	private String sortOrder;
	private String sortBy;

	public PageRequestBuilder withPage(int page) {
		this.page = page;
		return this;
	}

	public PageRequestBuilder withPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	
	public PageRequestBuilder withSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
	
	public PageRequestBuilder withSortBy(String sortBy) {
		this.sortBy = sortBy;
		return this;
	}

	public PageRequestDto build() {
		return new PageRequestDto(this);
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}
	
	

	

}
