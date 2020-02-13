package com.example.community.controller;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Derricker on 2020/2/2
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "8") Integer size,
                        @RequestParam(name = "search",required = false) String search){
        //跳转之前显示
        PaginationDTO<QuestionDTO> paginationDTO = questionService.listWithSearch(search,page,size);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }
}
