/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runmit.clotho.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author guojinhai_2001
 */
@Getter
@Setter
public class LoginUser {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String account;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String headposter;
    
}
