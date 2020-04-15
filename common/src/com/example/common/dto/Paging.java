package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
    private int totalPages;
    private Long totalElements;
    // the amount of elements each page
    private int numberOfElements;
    private int page;
    private boolean first;
    private boolean last;

    public Paging(){}

    public Paging(int totalPages, Long totalElements, int numberOfElements, int page, boolean first, boolean last) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.numberOfElements = numberOfElements;
        this.page = page;
        this.first = first;
        this.last = last;
    }

}
