package me.jaden.swipejobs.controller;

import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JobMatcherControllerTest {

    @Autowired
    JobMatcherController jobMatcherController;

    @Test
    public void testAllJobsMethod(){
        List<Job> j = jobMatcherController.listAllJobs();
        assertNotNull(j);
        assertEquals(j.size(), 40);
    }

    @Test
    public void testAllWorkerMethod(){
        List<Worker> w = jobMatcherController.listAllWorker();
        assertNotNull(w);
        assertEquals(w.size(), 50);
    }

    @Test
    public void testFindWorkerById(){
        Worker worker = jobMatcherController.findWorkerById(0);
        assertNotNull(worker);
        assertNotNull(worker.getFullName(), "Fowler Andrews");
    }

    @Test
    public void testFindJobById(){
        Job job = jobMatcherController.findJobById(0);
        assertNotNull(job);
        assertNotNull(job.getCompany(), "Frenex");
    }

    @Test
    public void testfindBestMatchJob() throws Exception{
        List<Job> matcheJobs1 = jobMatcherController.findMatchJob(16);
        assertNotNull(matcheJobs1);
        assertEquals(Optional.ofNullable(matcheJobs1.get(0).getJobId()).get().intValue(),  19);


        List<Job> matcheJobs2 = jobMatcherController.findMatchJob(6);
        assertNotNull(matcheJobs2);
        assertEquals(Optional.ofNullable(matcheJobs2.get(0).getJobId()).get().intValue(),  1);
    }
}