package me.jaden.swipejobs.service;

import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;

import java.util.Collection;
import java.util.List;

public interface JobMatcherService {

    List<Job> matchJobFirstThree(Worker worker, Collection<Job> jobs) throws Exception;

    Collection<Job> matchJobAll(Worker worker, Collection<Job> jobs) throws Exception;
}
