package com.alami.cooperation.vo;

import com.alami.cooperation.entity.Deposit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter @Setter
public class Pagination {

    private Object data;

    private Integer currentPage;

    private Long totalItems;

    private Integer totalPages;

    public Pagination(Object data, Integer currentPage, Long totalItems, Integer totalPages) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
