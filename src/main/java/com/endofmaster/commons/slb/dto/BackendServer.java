package com.endofmaster.commons.slb.dto;


/**
 * @author ZM.Wang
 * 后端服务器实体
 */
public class BackendServer {

    private String serverId;    //服务器id
    private String weight;      //权重
    private String type;        //类型，ecs，eni(弹性网卡)

    public BackendServer(String serverId, String weight, String type) {
        this.serverId = serverId;
        this.weight = weight;
        this.type = type;
    }

    BackendServer() {
    }

    public String getServerId() {
        return serverId;
    }

    public BackendServer setServerId(String serverId) {
        this.serverId = serverId;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public BackendServer setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getType() {
        return type;
    }

    public BackendServer setType(String type) {
        this.type = type;
        return this;
    }
}
