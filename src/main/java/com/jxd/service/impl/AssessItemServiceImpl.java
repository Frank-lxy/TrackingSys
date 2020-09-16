package com.jxd.service.impl;

import com.jxd.dao.IAssessItemDao;
import com.jxd.model.AssessItem;
import com.jxd.service.IAssessItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/12 15:33
 * @Version 1.0
 */
@Service
public class AssessItemServiceImpl implements IAssessItemService {
    @Autowired
    IAssessItemDao assessItemDao;

    @Override
    public boolean editAssessItem(AssessItem assessItem) {
        return assessItemDao.editAssessItem(assessItem);
    }

    @Override
    public AssessItem getAssessItemById(Integer assessItemId) {
        return assessItemDao.getAssessItemById(assessItemId);
    }

    @Override
    public boolean addAssessItem(AssessItem assessItem) {
        return assessItemDao.addAssessItem(assessItem);
    }

    @Override
    public AssessItem getAssessItemByName(String assessItemName) {
        return assessItemDao.getAssessItemByName(assessItemName);
    }

    @Override
    public boolean deleteAssessItemById(Integer assessItemId) {
        return assessItemDao.deleteAssessItemById(assessItemId);
    }

    @Override
    public List<AssessItem> getAssessItemPaging(Integer count, Integer pageSize, String assessItemName) {
        return assessItemDao.getAssessItemPaging(count,pageSize,assessItemName);
    }

    @Override
    public List<AssessItem> getAllAssessItem(String assessItemName) {
        return assessItemDao.getAllAssessItem(assessItemName);
    }
}
