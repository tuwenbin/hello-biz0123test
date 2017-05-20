package com.biz.std.service.impl;

import com.biz.std.model.Class;
import com.biz.std.model.Student;
import com.biz.std.repositories.ClassRepository;
import com.biz.std.service.ClassService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassRepository classRepository;

    @Transactional
    public void saveClass(ClassVO classVO) {
        Class classPO = new Class();
        BeanUtilsBean.VOConvertPO( classPO ,classVO);
        this.classRepository.save(classPO);
    }

    public List<ClassVO> findClassList() {
        Iterator<Class> classIterator = this.classRepository.findAll().iterator();
        List<ClassVO> classVOList = new ArrayList<ClassVO>();

        while (classIterator.hasNext()){
            ClassVO classVO = new ClassVO();
            Class classPO = classIterator.next();
            BeanUtilsBean.VOConvertPO(classVO,classPO);
            classVOList.add(classVO);
        }
        return classVOList;
    }

    public void updateClass(ClassVO classVO) {
        boolean flag = this.classRepository.exists(classVO.getCid());
        if(classVO.getCid()!=null && flag){
            Class studentPO = new Class();
            BeanUtilsBean.VOConvertPO(studentPO,classVO);
            this.classRepository.save(studentPO);
        }else{
            throw new RuntimeException("id为空或id不存在！");
        }
    }

    public void deleteClass(Long id) {
        this.classRepository.delete(id);
    }

    public ClassVO getClassById(Long id) {
        Class classPO = this.classRepository.findByCid(id);
        ClassVO classVO = new ClassVO();
        BeanUtilsBean.VOConvertPO(classVO,classPO);
        return classVO;
    }
}
