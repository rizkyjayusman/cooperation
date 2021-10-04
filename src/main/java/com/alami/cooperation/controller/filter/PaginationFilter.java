package com.alami.cooperation.controller.filter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginationFilter {

    private Integer page = 0;

    private Integer size = 10;

}
