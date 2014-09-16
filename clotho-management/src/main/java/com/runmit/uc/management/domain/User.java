package com.runmit.uc.management.domain;

import java.util.List;

/**
 *
 * @author TianLiang
 */
public class User {

    private Integer id;
    private String name;
    private Company company;
    private List<Address> ads;

    public List<Address> getAds() {
        return ads;
    }

    public void setAds(List<Address> ads) {
        this.ads = ads;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
