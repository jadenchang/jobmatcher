package me.jaden.swipejobs.service.impl;

import me.jaden.swipejobs.http.HttpService;
import me.jaden.swipejobs.json.JsonUtils;
import me.jaden.swipejobs.po.Worker;
import me.jaden.swipejobs.service.WorkerService;
import me.jaden.swipejobs.po.HttpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceHttpJsonImpl implements WorkerService {

    @Autowired
    JsonUtils jsonUtils;
    @Autowired
    HttpProperties httpProperties;
    @Autowired
    HttpService httpService;

    @Override
    public List<Worker> getAllWorkers() {
        List<Worker> workers = null;
        try {
            String workerJsonString = httpService.getJsonStringByURI(httpProperties::toWorkerURI);
            workers = jsonUtils.convertJsonToObject(workerJsonString, Worker.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public Optional<Worker> findWorkerById(Integer id) {
        return Optional.ofNullable(getAllWorkers().stream()
                .filter(w-> w.getUserId().equals(id)).collect(Collectors.toList()).get(0));
    }
}
