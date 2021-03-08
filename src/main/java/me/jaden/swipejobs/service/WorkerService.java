package me.jaden.swipejobs.service;

import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Optional<Worker> findWorkerById(Integer id);
}
