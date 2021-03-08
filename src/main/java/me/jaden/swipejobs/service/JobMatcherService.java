package me.jaden.swipejobs.service;

import me.jaden.swipejobs.exception.NotFoundException;
import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;

import java.util.Collection;
import java.util.List;

public interface JobMatcherService {

    static final int JOB_CAP = 3;

    List<Job> matchJobFirstThree(Worker worker, Collection<Job> jobs) throws NotFoundException;

    Collection<Job> matchJobAll(Worker worker, Collection<Job> jobs) throws NotFoundException;
}
