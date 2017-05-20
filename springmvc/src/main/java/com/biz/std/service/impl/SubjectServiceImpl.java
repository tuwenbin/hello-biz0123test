package com.biz.std.service.impl;

import com.biz.std.model.Subject;
import com.biz.std.repositories.SubjectRepository;
import com.biz.std.service.SubjectService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class  SubjectServiceImpl implements SubjectService{
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    public void saveSubjectVO(SubjectVO subjectVO) {
        Subject subjectPO = new Subject();
        BeanUtilsBean.VOConvertPO( subjectPO ,subjectVO);
        this.subjectRepository.save(subjectPO);
    }

    public List<SubjectVO> findSubjectVOList() {
        Iterator<Subject> subjectIterator = this.subjectRepository.findAll().iterator();
        List<SubjectVO> subjectVOList = new ArrayList<SubjectVO>();
        while (subjectIterator.hasNext()){
            SubjectVO subjectVO = new SubjectVO();
            Subject subjectPO = subjectIterator.next();
            BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
            subjectVOList.add(subjectVO);
        }
        return subjectVOList;
    }

    public void updateSubjectVO(SubjectVO subjectVO) {
        boolean flag = this.subjectRepository.exists(subjectVO.getSid());
        if(subjectVO.getSid()!=null && flag){
            Subject studentPO = new Subject();
            BeanUtilsBean.VOConvertPO(studentPO,subjectVO);
            this.subjectRepository.save(studentPO);
        }else{
            throw new RuntimeException("id为空或id不存在！");
        }
    }

    public void deleteSubjectVO(Long id) {
        this.subjectRepository.delete(id);
    }

    public SubjectVO getSubjectVOById(Long id) {
        Subject subjectPO = this.subjectRepository.findBySid(id);
        SubjectVO subjectVO = new SubjectVO();
        subjectVO.setSid(subjectPO.getSid());
        subjectVO.setName(subjectPO.getName());
        return subjectVO;
    }
}
