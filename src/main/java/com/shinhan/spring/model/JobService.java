package com.shinhan.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.spring.model.JobDAO;
import com.shinhan.spring.model.JobDTO;

@Service

public class JobService {
	
	@Autowired
	private JobService jobService;

	

    private final JobDAO jobDAO;

    public JobService(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    public List<JobDTO> getAllJobs() {
        return jobDAO.getAllJobs();
    }
    public boolean addJob(JobDTO job) {
        return jobDAO.addJob(job);
    }

    public boolean updateJob(JobDTO job) {
        return jobDAO.updateJob(job);
    }

    public boolean deleteJob(String jobId) {
        return jobDAO.deleteJob(jobId);
    }
}
