package com.service.uaa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer UserId;
    private String Username;
    private String Password;
    private List<String> Permissions;
}
