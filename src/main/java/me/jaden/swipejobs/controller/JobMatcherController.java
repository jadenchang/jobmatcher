package me.jaden.swipejobs.controller;

import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;
import me.jaden.swipejobs.service.JobMatcherService;
import me.jaden.swipejobs.service.JobService;
import me.jaden.swipejobs.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobMatcherController {

    @Autowired
    JobMatcherService jobMatcherService;
    @Autowired
    JobService jobService;
    @Autowired
    WorkerService workerService;

    @GetMapping("/findWorkerById/{userId}")
    public Worker findWorkerById(@PathVariable Integer userId){
        return workerService.findWorkerById(userId).orElse(null);
    }

    @GetMapping("/listAllWorkers")
    public List<Worker> listAllWorker(){
        return workerService.getAllWorkers();
    }

    @GetMapping("/findJobById/{jobId}")
    public Job findJobById(@PathVariable Integer jobId){
        return jobService.findJobById(jobId).orElse(null);
    }

    @GetMapping("/listAllJobs")
    public List<Job> listAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/findBestMatchJob/{userId}")
    public List<Job> findMatchJob(@PathVariable Integer userId)throws Exception{
        List<Job> jobs = jobService.getAllJobs();
        Worker worker = workerService.findWorkerById(userId).orElse(null);
        return jobMatcherService.matchJobFirstThree(worker, jobs);
    }

}
