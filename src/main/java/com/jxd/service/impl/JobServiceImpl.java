package com.jxd.service.impl;

import com.jxd.dao.IJobDao;
import com.jxd.model.Job;
import com.jxd.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/12 18:46
 * @Version 1.0
 */
@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    IJobDao jobDao;

    @Override
    public boolean editJob(Job job) {
        return jobDao.editJob(job);
    }

    @Override
    public Job getJobById(Integer jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public boolean addJob(Job job) {
        return jobDao.addJob(job);
    }

    @Override
    public Job getJobByName(String jobName) {
        return jobDao.getJobByName(jobName);
    }

    @Override
    public boolean deleteJobById(Integer jobId) {
        return jobDao.deleteJobById(jobId);
    }

    @Override
    public List<Job> getJobPaging(Integer count, Integer pageSize, String jobName) {
        return jobDao.getJobPaging(count, pageSize, jobName);
    }

    @Override
    public List<Job> getAllJob() {
        return jobDao.getAllJob();
    }
}
