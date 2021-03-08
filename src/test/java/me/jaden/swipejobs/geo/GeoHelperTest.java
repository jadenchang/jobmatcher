package me.jaden.swipejobs.geo;


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

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GeoHelperTest {

    @Autowired
    JobMatcherService jobMatcherService;
    @Autowired
    JobService jobService;
    @Autowired
    WorkerService workerService;

    /*
        Test the Geo Distance
     */
    @Test
    public void TestGeoForumla(){
        double xyz = GeoHelper.getDistance(13.864602, 49.93359, 13.971284, 49.782281);
        Assert.assertEquals(xyz, 18.0, 0);
    }

    /*
        Test if the worker accept the job distance or not, position case
     */
    @Test
    public void TestWithinZone(){
        Job job = jobService.findJobById(0).get();
        Worker worker = workerService.findWorkerById(0).get();

        boolean acceptDistance = worker.getJobSearchAddress().in(job.getLocation().get("longitude"), job.getLocation().get("latitude"));
        Assert.assertTrue(acceptDistance);
    }

    /*
       Test if the worker accept the job distance or not, negative case
    */
    @Test
    public void TestOutOfZone(){
        Job job = jobService.findJobById(1).get();
        Worker worker = workerService.findWorkerById(0).get();

        boolean acceptDistance = worker.getJobSearchAddress().in(job.getLocation().get("longitude"), job.getLocation().get("latitude"));
        Assert.assertFalse(acceptDistance);
    }
}