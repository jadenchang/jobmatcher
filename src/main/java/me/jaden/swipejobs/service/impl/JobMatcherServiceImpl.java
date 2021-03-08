package me.jaden.swipejobs.service.impl;

import me.jaden.swipejobs.exception.NotFoundException;
import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;
import me.jaden.swipejobs.service.JobMatcherService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobMatcherServiceImpl implements JobMatcherService {

    /**
     * find all matched jobs
     * @param worker
     * @param jobs
     * @return
     * @throws Exception
     */
    @Override
    public List<Job> matchJobFirstThree(Worker worker, Collection<Job> jobs) throws NotFoundException {
        List<Job> retVal = null;
        try {
            Collection<Job> allMatchedJobs = matchJobAll(worker, jobs);

            List<Job> sortedJob = allMatchedJobs.stream().sorted(Comparator.comparing(Job::getScore).reversed()).collect(Collectors.toList());

            //return first 3 if available
            int size = 0;
            if( (size = sortedJob.size())>0){
                if(size>=JOB_CAP) size=JOB_CAP;
                retVal = sortedJob.subList(0, size);
            }
        }catch(NotFoundException e){
            throw e;
        }

        return retVal;
    }

    /**
     * the match criteria are enlisted below. if matched, get a core
     * a.) jobTitle: Jobs <->  skill: Workers
     * b.) requiredCertificates: Jobs <->  certificates: Workers
     * c.) location: Jobs <->  jobSearchAddress: Workers   [ONLY keep the jobs which has acceptable distance.]
     * d.) driverLicenseRequired:Jobs <-> hasDriversLicense: Workers [ONLY keep the jobs which match the driver license.]
     * if driverLicenseRequired of job is true, then the worker must comply with the condition.
     * @param worker
     * @param jobs
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Job> matchJobAll(Worker worker, Collection<Job> jobs)throws NotFoundException{
        if(worker==null) throw new NotFoundException();

        //1. jobTitle: Jobs <->  skill: Workers
        jobs.stream().filter(job-> worker.getSkills().contains(job.getJobTitle())).forEach(Job::score);

        //2. requiredCertificates: Jobs <->  certificates: Workers
        List<Job> filteredJobs = jobs.stream().filter(job -> {
            long matchedCertCount = job.getRequiredCertificates().stream()
                    .filter(cert -> worker.getCertificates().contains(cert)).count();
            if(matchedCertCount>0) return true;
            return false;
        }).collect(Collectors.toList());

        filteredJobs.stream().forEach(job -> {
            long matchedCertCount = job.getRequiredCertificates().stream()
                    .filter(cert -> worker.getCertificates().contains(cert)).count();
            if(matchedCertCount>0) job.score();
        });

        //3. location: Jobs <->  jobSearchAddress: Workers,  [ONLY keep the jobs which has acceptable distance.]
        filteredJobs = filteredJobs.stream().filter(job -> {
            Map<String, Double> location = job.getLocation();
            return worker.getJobSearchAddress().in(location.get("longitude"), location.get("latitude"));
        }).collect(Collectors.toList());


        //4. driverLicenseRequired:Jobs <-> hasDriversLicense: Workers [ONLY keep the jobs which match the driver license.]
        filteredJobs = filteredJobs.stream().filter(job -> {
            if(job.isDriverLicenseRequired() && !worker.isDriversLicense()) {
                return false;
            }
            return true;
         }).collect(Collectors.toList());

        return filteredJobs;
    }

}
