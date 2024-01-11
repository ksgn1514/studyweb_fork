package com.weavus.studyweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;

import com.weavus.studyweb.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long id;
    private String userid;
    private String password;
    private String username;
    private String role;
    private String createDate;

    public static UserDTO toUserdto(User user) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserid(user.getUserid());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setCreateDate(dateFormat.format(user.getCreateDate()));

        return userDto;
    }
}