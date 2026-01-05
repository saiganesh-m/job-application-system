package com.zenith.firstjobapp.job;
import java.util.*;

public interface JobService {
    List<Job> findAllJobs();
    Job findJobById(Long id);
    boolean createJob(Job job);
    boolean updateJobById(Long id, Job updatedJob);
    boolean deleteJobById(Long id);
}
