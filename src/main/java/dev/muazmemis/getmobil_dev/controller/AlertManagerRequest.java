package dev.muazmemis.getmobil_dev.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL) // Boş alanları yok say
public class AlertManagerRequest {

    @JsonProperty("version")
    private String version;

    @JsonProperty("externalURL")
    private String externalURL;

    @JsonProperty("hostName")
    private String hostName;

    @JsonProperty("groupKey")
    private String groupKey;

    @JsonProperty("status")
    private String status;

    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("alerts")
    private List<Alert> alerts;

    // Getter & Setter'lar

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setExternalURL(String externalURL) {
        if (externalURL == null || externalURL.isEmpty())
            this.externalURL = "";

        this.externalURL = externalURL.replaceAll("^http://", "").replaceAll(":.*", "");
    }

    public String getExternalURL() {
        return externalURL;
    }

    public static class Alert {
//        @JsonProperty("status")
//        private String status;

        @JsonProperty("labels")
        private Map<String, String> labels;

        @JsonProperty("annotations")
        private Map<String, String> annotations;

        @JsonProperty("startsAt")
        private String startsAt;

//        @JsonProperty("endsAt")
//        private String endsAt;

        // Getter & Setter'lar
    }

    @Override
    public String toString() {
        return "AlertManagerRequest{" +
                "version='" + version + '\'' +
                ", externalURL='" + externalURL + '\'' +
                ", hostName='" + hostName + '\'' +
                ", groupKey='" + groupKey + '\'' +
                ", status='" + status + '\'' +
                ", receiver='" + receiver + '\'' +
                ", alerts=" + alerts +
                '}';
    }
}

