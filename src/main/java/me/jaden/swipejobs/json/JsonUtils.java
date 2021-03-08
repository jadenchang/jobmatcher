package me.jaden.swipejobs.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jaden.swipejobs.po.Worker;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonUtils {

    /**
     * convert json string to specific instance by give class.
     */
    public <T> List<T> convertJsonToObject(String jsonStr, Class<T> targetClass) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        List<T> retList = null;

        JavaType clazzType = objectMapper.getTypeFactory().constructParametricType(List.class, targetClass);
        retList = objectMapper.readValue(jsonStr, clazzType);
        return retList;
    }
}
