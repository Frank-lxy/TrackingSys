package com.jxd.service.impl;

import com.jxd.dao.ISassessDao;
import com.jxd.model.Sassess;
import com.jxd.model.User;
import com.jxd.service.ISassessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:Chai HongFang
 * @Description:
 * @Date:2020/9/12 11:01
 * @Version:1.0
 */

@Service
public class SassessServiceImpl implements ISassessService {
    @Autowired
    ISassessDao sassessDao;
    @Override
    public List<Map<String, Object>> getAssessByPage(String studentName, String userName,Integer classId, Integer count, Integer page) {
        return sassessDao.getAssessByPage(studentName,userName,classId,count,page);
    }

    @Override
    public boolean addSassess(Sassess sassess) {
        return sassessDao.addSassess(sassess);
    }

    @Override
    public boolean editSassess(Sassess sassess) {
        return sassessDao.editSassess(sassess);
    }

    @Override
    public Sassess getSassessById(Integer sassessId) {
        return sassessDao.getSassessById(sassessId);
    }

    @Override
    public List<Map<String, Object>> getAllAssess(String userName) {
        return sassessDao.getAllAssess(userName);
    }

    @Override
    public Sassess getSassessByStuId(Integer studentId) {
        return sassessDao.getSassessByStuId(studentId);
    }

    @Override
    public User getUserById(Integer userId) {
        return sassessDao.getUserById(userId);
    }
}
