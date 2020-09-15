package com.jxd.service;

import com.jxd.model.Sassess;
import com.jxd.model.User;

import java.util.List;
import java.util.Map;

public interface ISassessService {

    /**
     * 分页查询评价信息
     * @param studentName 学生姓名
     * @param classId 班级编号
     * @param count 跳过几条数据
     * @param page 选择几条数据
     * @return 评价信息集合
     */
    List<Map<String,Object>> getAssessByPage(String studentName, String userName, Integer classId, Integer count, Integer page);

    /**
     * 增加评价
     * @param sassess 评价对象
     * @return 是否评价成功
     */
    boolean addSassess(Sassess sassess);

    /**
     * 修改评价
     * @param sassess 评价对象
     * @return 是否修改成功
     */
    boolean editSassess(Sassess sassess);

    /**
     * 根据评价id查找评价
     * @param sassessId 评价id
     * @return 评价对象
     */
    Sassess getSassessById(Integer sassessId);

    /**
     * 查询全部评价信息
     * @return
     */
    List<Map<String,Object>> getAllAssess(String userName);

    /**
     * 根据学生id查找评价
     * @param studentId
     * @return
     */
    Sassess getSassessByStuId(Integer studentId);

    /**
     * 通过用户id得到用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);
}
