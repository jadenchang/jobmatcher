package me.jaden.swipejobs.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Job {

    @JsonProperty("driverLicenseRequired")
    private boolean driverLicenseRequired;
    @JsonProperty("requiredCertificates")
    private List<String> requiredCertificates;
    @JsonProperty("location")
    private Map<String, Double> location;
    @JsonProperty("billRate")
    private String billRate;

    @JsonProperty("workersRequired")
    private Integer workersRequired;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("about")
    private String about;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("company")
    private String company;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("jobId")
    private Integer jobId;

    private Integer score = 0;

    public void score(){ this.score++; }
}
