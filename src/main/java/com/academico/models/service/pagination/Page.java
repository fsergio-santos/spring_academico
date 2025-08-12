package com.academico.models.service.pagination;

import java.util.List;


public class Page<T> {

    private final List<T> content;
    private final int  totalPages;
    private final long totalElements;
    private final int  pageSize;
    private final int  page;
    private final int  offSet;

    private Page(Builder<T> builder) {
        this.content = builder.content;
        this.totalPages = builder.totalPages;
        this.totalElements = builder.totalElements;
        this.pageSize = builder.pageSize;
        this.page = builder.page;
        this.offSet = builder.offSet;
    }


    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }
    
    public int getOffSet() {
		return offSet;
	}


	public static <T> Page<T> createPagination(
    		List<T> content, 
    		Integer totalElements,
    		Integer pageSize, 
    		Integer page
    		) {
    	    int totalPages = calcularTotalPages(totalElements, pageSize);
    	    int offSet = calculateOffset(page, pageSize);
        return new Page.Builder<T>()
                .content(content)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .pageSize(pageSize)
                .page(page)
                .offSet(offSet)
                .build();
    }
    
    private static int calcularTotalPages(int totalEments, int pageSize) {
    	return (int) totalEments/pageSize;
    }
    
    public static int calculateOffset(int page, int pageSize) {
        return page * pageSize;
    }

    public static class Builder<T> {
        private List<T> content;
        private int totalPages;
        private long totalElements;
        private int pageSize;
        private int page;
        private int offSet;

        public Builder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder<T> totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> page(int page) {
            this.page = page;
            return this;
        }
        
        public Builder<T> offSet(int offSet) {
            this.offSet = offSet;
            return this;
        }

        public Page<T> build() {
            return new Page<>(this);
        }
    }
}