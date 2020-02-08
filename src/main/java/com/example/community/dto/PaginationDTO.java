package com.example.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类的目的在于承载所有页面需要的元素，将其整合在一起，作为传输层数据
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer totalPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size+1;
        }

        if(page<1)
            page = 1;
        if(page>totalPage)
            page = totalPage;
        this.page = page;
        pages.add(page);
        for(int i = 1;i<=3;i++){
            if(page-i>0)
                pages.add(0,page-i);
            if(page+i<=totalPage)
                pages.add(page+i);
        }

        //是否展示上一页按钮
        if(page == 1)
            showPrevious = false;
        else
            showPrevious = true;

        //是否展示下一页按钮
        if(page == totalPage)
            showNext = false;
        else
            showNext = true;

        //是否展示第一页按钮
        if(pages.contains(1))
            showFirstPage = false;
        else
            showFirstPage = true;

        //是否展示最后一页按钮
        if(pages.contains(totalPage))
            showEndPage = false;
        else
            showEndPage = true;

    }
}
