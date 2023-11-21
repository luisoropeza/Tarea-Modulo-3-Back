package com.diplomado.demo.services.mapper;

import org.springframework.stereotype.Component;

import com.diplomado.demo.dtos.UserDetailedDto;
import com.diplomado.demo.dtos.UserDto;
import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
import com.diplomado.demo.models.UserDetailEntity;
import com.diplomado.demo.models.UserEntity;

@Component
public class UserMapper {
    public UserDto toUserDto(UserEntity user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserDetailedDto toUserDetailedDto(UserEntity user) {
        UserDetailedDto dto = new UserDetailedDto();
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
    };

    public UserEntity toUserEntity(CreateUserWithRolesRequest dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setUserDetail(
                new UserDetailEntity(dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getBirthDay(), user));
        return user;
    }

    public UserEntity toUserEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
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

    public UserDto toUserDto(CreateUserWithRolesRequest user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
