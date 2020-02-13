package com.example.community.service;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 起到组装作用，当一个请求需要组装两个不同的数据库对象的时候，使用service起到中间作用
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO<QuestionDTO> list(String accountId, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByUserId(accountId);
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(totalCount,page,size);

        Integer offset = size*(paginationDTO.getPage()-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question:questions){
            User user = userMapper.findByAccountId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //快速把前对象伤的所有属性拷贝到之后的对象中,名称相同
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.getById(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findByAccountId(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //更新
            if(questionMapper.getById(question.getId()) == null)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            question.setGmtModify(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

    public void incView(Long id) {
        questionMapper.updateViewCount(id);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDTO.getTag(), "/");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        List<Question> questions = questionMapper.listByRegexp(regexpTag, questionDTO.getId());
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO tmpDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,tmpDTO);
            return tmpDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    public PaginationDTO<QuestionDTO> listWithSearch(String search, Integer page, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
            List<Question> searchQuestions = questionMapper.listBySearch(search);
            Integer totalCount = searchQuestions.size();
            paginationDTO.setPagination(totalCount,page,size);

            Integer offset = size*(paginationDTO.getPage()-1);
            if(offset<0)
                offset = 0;
            List<QuestionDTO> questionDTOList = new ArrayList<>();


            for(int i = offset;i<searchQuestions.size();i++){
                User user = userMapper.findByAccountId(searchQuestions.get(i).getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                //快速把前对象伤的所有属性拷贝到之后的对象中,名称相同
                BeanUtils.copyProperties(searchQuestions.get(i),questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setData(questionDTOList);
        }else{
            Integer totalCount = questionMapper.count();

            paginationDTO.setPagination(totalCount,page,size);

            Integer offset = size*(paginationDTO.getPage()-1);
            List<Question> questions = questionMapper.list(offset, size);
            List<QuestionDTO> questionDTOList = new ArrayList<>();

            for(Question question:questions){
                User user = userMapper.findByAccountId(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                //快速把前对象伤的所有属性拷贝到之后的对象中,名称相同
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setData(questionDTOList);
        }


        return paginationDTO;
    }
}
