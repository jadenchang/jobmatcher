package me.jaden.swipejobs.service.impl;

import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;
import me.jaden.swipejobs.service.JobMatcherService;
import me.jaden.swipejobs.service.JobService;
import me.jaden.swipejobs.service.WorkerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JobMatcherServiceImplTest {

    @Autowired
    JobMatcherService jobMatcherService;
    @Autowired
    JobService jobService;
    @Autowired
    WorkerService workerService;

    @Test
    public void runBestMatchTest()throws Exception{
        Integer testWorkerId = 16;
        Integer expectedBestJob = 19;
        List<Job> jobs = jobService.getAllJobs();
        Worker worker = workerService.findWorkerById(testWorkerId).orElse(null);
        List<Job> js = jobMatcherService.matchJobFirstThree(worker, jobs);

        Assert.assertEquals(Optional.ofNullable(js.get(0).getJobId()).get().intValue(),  expectedBestJob.intValue());
    }
}