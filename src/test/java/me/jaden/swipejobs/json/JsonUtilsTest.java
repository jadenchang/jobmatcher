package me.jaden.swipejobs.json;

import me.jaden.swipejobs.http.HttpService;
import me.jaden.swipejobs.po.Job;
import me.jaden.swipejobs.po.Worker;
import me.jaden.swipejobs.po.HttpProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonUtilsTest {

    @Autowired
    HttpService httpService;

    @Autowired
    HttpProperties httpProperties;
    @Autowired
    JsonUtils jsonUtils;

    String workerJsonString;

    String jobJsonString;

    @Before
    public void setup(){
        try {
            URI url = httpProperties.toWorkerURI();
            workerJsonString = httpService.getResponseBody(url, HttpService.Method.GET);

            url = httpProperties.toJobURI();
            jobJsonString = httpService.getResponseBody(url, HttpService.Method.GET);
        }catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(workerJsonString);
    }

    @Test
    public void testWorkerJsonConverter() throws Exception {
        List<Worker> workers = jsonUtils.convertJsonToObject(workerJsonString, Worker.class);
        Assert.assertNotNull(workers.get(0));
    }


    @Test
    public void testJobJsonConvert() throws Exception {
        List<Job> workers = jsonUtils.convertJsonToObject(jobJsonString, Job.class);
        Assert.assertNotNull(workers.get(0));
    }
}


