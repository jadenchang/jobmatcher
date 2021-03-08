package me.jaden.swipejobs.service;

import me.jaden.swipejobs.po.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> getAllJobs();

    Optional<Job> findJobById(Integer id);


}
