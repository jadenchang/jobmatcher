package me.jaden.swipejobs.http.impl;

import me.jaden.swipejobs.exception.DataSourceNotReadyException;
import me.jaden.swipejobs.http.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.function.Supplier;

@Service
public class ApacheHttpClientServiceImpl implements HttpService {

    @Autowired
    HttpService  httpService;

    @Override
    public String getJsonStringByURI(Supplier<URI> s) throws DataSourceNotReadyException {
        String retVal;
        try {
            retVal = httpService.getResponseBody(s.get(), HttpService.Method.GET);
        }catch(Exception e){
            throw new DataSourceNotReadyException(e.getMessage());
        }
        return retVal;
    }

    @Override
    public String getResponseBody(java.net.URI uri, Method method) throws DataSourceNotReadyException {
        String s = null;
        try {
            HttpClient client = initClient(uri, Method.GET);
            HttpResponse httpResponse = client.execute(new HttpGet(uri));
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                s = EntityUtils.toString(entity);
            }
        } catch (ClientProtocolException e) {
            throw new DataSourceNotReadyException(e.getMessage());
        } catch (IOException ioe) {
            throw new DataSourceNotReadyException(ioe.getMessage());
        }
        return s;
    }

    private HttpClient initClient(java.net.URI uri, Method method) {
            return HttpClientBuilder.create().build();
    }
}
