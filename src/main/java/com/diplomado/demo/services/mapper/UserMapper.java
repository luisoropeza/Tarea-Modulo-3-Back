package com.diplomado.demo.services.mapper;

import org.springframework.stereotype.Component;

import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
import com.diplomado.demo.dtos.Response.UserResponse;
import com.diplomado.demo.dtos.Response.UserDetailedResponse;
import com.diplomado.demo.models.UserDetailEntity;
import com.diplomado.demo.models.UserEntity;

@Component
public class UserMapper {
    public UserResponse toUserResponse(UserEntity user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserResponse toUserResponse(CreateUserWithRolesRequest user) {
        UserResponse dto = new UserResponse();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserDetailedResponse toUserDetailedResponse(UserEntity user) {
        UserDetailedResponse dto = new UserDetailedResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        if (user.getUserDetail() != null) {
            dto.setFirstName(user.getUserDetail().getFirstName());
            dto.setLastName(user.getUserDetail().getLastName());
            dto.setAge(user.getUserDetail().getAge());
            dto.setBirthDay(user.getUserDetail().getBirthDay());
        } else {
            dto.setFirstName("Not assigned yet");
            dto.setLastName("Not assigned yet");
            dto.setAge(null);
            dto.setBirthDay(null);
        }
        return dto;
    }

    public UserEntity userCreate(UserResponse dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserEntity userDetailCreate(CreateUserWithRolesRequest dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setUserDetail(
                new UserDetailEntity(dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getBirthDay(), user));
        return user;
    }

    public UserEntity userUpdate(UpdateUserRequest dto, UserEntity user, UserDetailEntity userDetail) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        if (userDetail != null) {
            userDetail.setFirstName(dto.getFirstName());
            userDetail.setLastName(dto.getLastName());
            userDetail.setAge(dto.getAge());
            userDetail.setBirthDay(dto.getBirthDay());
            user.setUserDetail(userDetail);
        } else {
            user.setUserDetail(
                    new UserDetailEntity(dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getBirthDay(), user));
        }
        return user;
    }
}
