package me.jaden.swipejobs.http;

import me.jaden.swipejobs.exception.DataSourceNotReadyException;

import java.net.URI;
import java.util.function.Supplier;

public interface HttpService {

    enum Method{
        GET,
        POST
    }

    String getResponseBody(java.net.URI uri, Method method) throws DataSourceNotReadyException;

    String getJsonStringByURI(Supplier<URI> s) throws DataSourceNotReadyException;
}
