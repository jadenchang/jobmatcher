package me.jaden.swipejobs.service.impl;

import me.jaden.swipejobs.http.HttpService;
import me.jaden.swipejobs.json.JsonUtils;
import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.service.JobService;
import me.jaden.swipejobs.po.HttpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceHttpJsonImpl implements JobService {

    @Autowired
    JsonUtils jsonUtils;
    @Autowired
    HttpProperties httpProperties;
    @Autowired
    HttpService httpService;

    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs = null;
        try {
            String jobJsonString = httpService.getJsonStringByURI(httpProperties::toJobURI);
            jobs = jsonUtils.convertJsonToObject(jobJsonString, Job.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public Optional<Job> findJobById(Integer id) {
        if(id==null) return Optional.empty();
        return Optional.ofNullable(getAllJobs().stream().filter(n->n.getJobId().equals(id))
                .collect(Collectors.toList()).get(0));
    }
}
