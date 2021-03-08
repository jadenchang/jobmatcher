package me.jaden.swipejobs.po;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Worker {
    private Integer rating;
    @JsonProperty("isActive")
    private boolean active;
    private List<String> certificates;
    private List<String> skills;
    private JobSearchAddress jobSearchAddress;
    private String transportation;
    @JsonProperty("hasDriversLicense")
    private boolean driversLicense;
    private List<Map<String, String>> availability;
    private String phone;
    private String email;
    private Map<String, String> name;
    private Integer age;
    private String guid;
    private Integer userId;


    public String getFullName(){
        if(name==null || name.size()<1) return "";
        return String.format("%s %s", name.get("first"), name.get("last"));
    }

}
