package com.zenith.firstjobapp.job.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenith.firstjobapp.job.Job;
import com.zenith.firstjobapp.job.JobRepository;
import com.zenith.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

    // private Long nextID = 1L;
    // private List<Job> jobList = new ArrayList<>();
    @Autowired
    JobRepository jobRepository;

    // public JobServiceImpl(JobRepository jobRepository) {
    //     this.jobRepository = jobRepository;
    // }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createJob(Job job) {
        // job.setId(nextID++);
        jobRepository.save(job);
        return true;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> existingJobOpt = jobRepository.findById(id);
        if (existingJobOpt.isPresent()) {
            Job existingJob = existingJobOpt.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            jobRepository.save(existingJob);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
