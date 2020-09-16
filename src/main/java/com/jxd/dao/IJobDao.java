package com.jxd.dao;

import com.jxd.model.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IJobDao {

    /**
     * 编辑职务
     * @param job 要编辑的职务
     * @return 是否编辑成功
     */
    boolean editJob(Job job);

    /**
     * 根据id查询职务信息
     * @param jobId 职务id
     * @return 课程信息
     */
    Job getJobById(Integer jobId);

    /**
     * 新增职务
     * @param job 要新增的职务
     * @return 是否增加成功
     */
    boolean addJob(Job job);

    /**
     * 根据职务名称查询该职务是否存在
     * @param jobName 职务名称
     * @return 是否存在
     */
    Job getJobByName(String jobName);

    /**
     * 根据id删除职务
     * @param jobId 职务id
     * @return 是否删除成功
     */
    boolean deleteJobById(Integer jobId);

    /**
     * 分页查询职务
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param jobName 职务名称
     * @return 职务列表
     */
    List<Job> getJobPaging(@Param("count") Integer count,@Param("pageSize") Integer pageSize,@Param("jobName") String jobName);

    /**
     * 获取职务列表
     * @return 职务列表
     */
    List<Job> getAllJob();
}
