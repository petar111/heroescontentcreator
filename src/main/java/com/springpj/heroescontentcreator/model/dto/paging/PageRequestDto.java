package com.springpj.heroescontentcreator.model.dto.paging;

public class PageRequestDto {
	
		private final int page;
		private final int pageSize;
		private final String sortOrder;
		private final String sortBy;
		
		PageRequestDto(PageRequestBuilder builder) {
			this.page = builder.getPage();
			this.pageSize = builder.getPageSize();
			this.sortOrder = builder.getSortOrder();
			this.sortBy = builder.getSortBy();
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

		public static PageRequestBuilder builder() {
			return new PageRequestBuilder();
		}
}
