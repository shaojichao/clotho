package com.runmit.clotho.rest.domain;

import java.util.List;

/**
 * Created by Qian.Liu on 2016/6/28.
 */
public class ClientResp {
    private int rtn;

    private String clientIdLists;

    public String getClientIdLists() {
        return clientIdLists;
    }

    public void setClientIdLists(String clientIdLists) {
        this.clientIdLists = clientIdLists;
    }

    public int getRtn() {
        return rtn;
    }

    public void setRtn(int rtn) {
        this.rtn = rtn;
    }
}
