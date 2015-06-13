package com.crux.demo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aaronstone on 6/12/15.
 */
@XmlRootElement(name = "apiHost")
public class ApiHost {

    private static ApiHost instance;

    private String hostAddr;
    private int port;
    private String apiBasePath;

    private ApiHost() {
    }

    public static ApiHost getInstance() {
        if(instance == null) {
            instance = new ApiHost();
        }

        return instance;
    }

    @XmlElement(name = "hostAddr")
    public String getHostAddr() {
        return hostAddr;
    }

    public void setHostAddr(String hostAddr) {
        this.hostAddr = hostAddr;
    }

    @XmlElement(name = "port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @XmlElement(name = "apiBasePath")
    public String getApiBasePath() {
        return apiBasePath;
    }

    public void setApiBasePath(String apiBasePath) {
        this.apiBasePath = apiBasePath;
    }
}
