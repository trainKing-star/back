package com.service.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authorize {

    private String client_id;

    private String response_type;

    private String scope;

    private String redirect_uri;

}
