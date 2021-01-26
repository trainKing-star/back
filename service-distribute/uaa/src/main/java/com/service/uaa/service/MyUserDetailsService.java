package com.service.uaa.service;

import com.service.uaa.mapper.UserMapper;
import com.service.uaa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(s);
        if (user == null) {
            return null;
        }

        String[] permissionArray = new String[user.getPermissions().size()];
        user.getPermissions().toArray(permissionArray);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()).password(user.getPassword()).authorities(permissionArray).build();

    }
}
