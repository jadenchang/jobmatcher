#JobMatcher

## Prerequisite

- JDK: above 1.8
- Maven: above 3.6

## Technical Stack

- Java Core: 1.8
- Spring-boot: 2.4.3
- Apache Http Client:4.5.12
- fasterxml/jackson
- Lombok:1.18.16
- JUnit:4.13

---
## How to build [Maven]
make sure you have maven installed in your local computer.
```shell
> mvn clean package
```

## How to run with maven plugin

```shell 
> mvn spring-boot:run
```
---

## How to build with [Docker]

```shell

> mvn clean package

> docker build . 
```

## The EntryPoint

the base http url is `http://localhost:127.0.0.1:8080`

- `/findWorkerById/{userId}` : find the worker info by given id
> http://127.0.0.1:8080/findWorkerById/0
- `/listAllWorkers` : list all workers information
> http://127.0.0.1:8080/listAllWorkers
- `/findJobById/{jobId}`: find the job info by given id
> http://127.0.0.1:8080//findJobById/0
- `/listAllJobs` : list all jobs information
> http://127.0.0.1:8080/listAllJobs
- `/findBestMatchJob/{userId}` : find the best 3 job available for workers.
> http://127.0.0.1:8080/findBestMatchJob/0

e.g. the the bast 3 jobs for userId-0 -> http://127.0.0.1:8080/findBestMatchJob/0

---

## Project Description

Jaden implements the converter to translate json string resources on http URL into java object for Worker and Job, 
then create access services for both objects, and implement a service is used to match job and worker. the matching 
conditions are enlisted below.

1. jobTitle: Jobs <->  skill: Workers
2. requiredCertificates: Jobs <->  certificates: Workers
   > the certification requirment must be satisified by Worker's owning certification.
3. location: Jobs <->  jobSearchAddress: Workers [ONLY keep the jobs which has acceptable distance.]   
   > the job must be in the distance which worker can accept.
4. driverLicenseRequired:Jobs <-> hasDriversLicense: Workers [ONLY keep the jobs which match the driver license.] 
   > if driverLicenseRequired of job is true, then the worker must comply with the condition.

Jaden also implements some test cases to verify the unit of programs is workable.

##base package 

the base package is `me.jaden.swipejobs`.

- `me.jaden.swipejobs.controller.*`: Entrypoint for the Rest API.
- `me.jaden.swipejobs.exception.*`: Exception definition.
- `me.jaden.swipejobs.geo.*`: Geo utilization to calculation the distance.
- `me.jaden.swipejobs.http.*`: Http utilization to pull the json resources via Http
- `me.jaden.swipejobs.json.*`: Json utilization to convert json into corresponding class.
- `me.jaden.swipejobs.po.*`: plain java object to contain the value of resources.
- `me.jaden.swipejobs.service.*`: all the service interface.
- `me.jaden.swipejobs.service.impl.*`: all the service implementation.


## Test Cases

**GeoHelperTest**
> 1. `TestGeoForumla`: test the distance betweeen two locations.
> 2. `TestWithinZone`: test if the distance is acceptable by threshold.
> 3. `TestOutOfZone`: test if the distance is NOT acceptable by threshold.

**JsonUtilsTest**
> 1. `testWorkerJsonConverter`: test if the json string of `worker` is available and can be convert to JSON object;
> 2. `testJobJsonConvert`test if the json string of `Job` is available and can be convert to JSON object;

**JobMatcherServiceImplTest**
> 1. `JobMatcherServiceImplTest`: test if best match for jobs.

## Enhancement (TO BE DONE)
The below items are what I can improve in the future.

- Provide the entry point by give worker's characters : can use GraphQL
- Use swagger to provider a better API documentation and test case.
- Use NTL(Natural Language Processing) to match the job description, skills, certification better.
- Provide more error handling.
- Provide UI for query.