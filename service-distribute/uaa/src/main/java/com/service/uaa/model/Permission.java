package com.service.uaa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    private Integer PermissionId;
    private String name;
    private Integer UId;

}
