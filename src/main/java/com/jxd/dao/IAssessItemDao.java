package com.jxd.dao;

import com.jxd.model.AssessItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAssessItemDao {

    /**
     * 编辑评分项
     * @param assessItem 要编辑的评分项
     * @return 是否编辑成功
     */
    boolean editAssessItem(AssessItem assessItem);

    /**
     * 根据课程id查询评分项信息
     * @param assessItemId 评分项id
     * @return 评分项信息
     */
    AssessItem getAssessItemById(Integer assessItemId);

    /**
     * 新增评价项
     * @param assessItem 要新增的评价项
     * @return 是否增加成功
     */
    boolean addAssessItem(AssessItem assessItem);

    /**
     * 根据评价项名称查询评价项是否存在
     * @param assessItemName 评价项名称
     * @return 是否存在
     */
    AssessItem getAssessItemByName(String assessItemName);

    /**
     * 根据id删除评价项
     * @param assessItemId 评价项id
     * @return 是否删除成功
     */
    boolean deleteAssessItemById(Integer assessItemId);

    /**
     * 分页查询评价项
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param assessItemName 评价项名称
     * @return 课程列表
     */
    List<AssessItem> getAssessItemPaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("assessItemName") String assessItemName);

    /**
     * 获取评价项列表
     * @return 评价项列表
     */
    List<AssessItem> getAllAssessItem(@Param("assessItemName") String assessItemName);
}
