package com.diplomado.demo.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
import com.diplomado.demo.dtos.Response.UserResponse;
import com.diplomado.demo.dtos.Response.UserDetailedResponse;
import com.diplomado.demo.models.UserDetailEntity;
import com.diplomado.demo.models.UserEntity;
import com.diplomado.demo.models.UserRolEntity;
import com.diplomado.demo.repositories.data.jpa.UserDetailRepository;
import com.diplomado.demo.repositories.data.jpa.UserRepository;
import com.diplomado.demo.repositories.data.jpa.UserRolRepository;
import com.diplomado.demo.services.UserRolService;
import com.diplomado.demo.services.UserService;
import com.diplomado.demo.services.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserRolRepository userRolRepository;
    private final UserRolService userRolService;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<?> getAllUsers(Boolean detailed) {
        if (detailed) {
            return this.getAllUsersDetailed();
        } else {
            return this.getAllUsersNoDetailed();
        }
    }

    public List<UserResponse> getAllUsersNoDetailed() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    public List<UserDetailedResponse> getAllUsersDetailed() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserDetailedResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailedResponse createUser(CreateUserWithRolesRequest dto) {
        UserEntity user = new UserEntity();
        if (dto.getFirstName() != null && dto.getLastName() != null) {
            user = userRepository.save(userMapper.userDetailCreate(dto));
        } else {
            user = userRepository.save(userMapper.userCreate(userMapper.toUserResponse(dto)));
        }
        if (dto.getRolesId() != null) {
            userRolService.assignRolesToUser(user.getId(), dto.getRolesId());

        }
        return userMapper.toUserDetailedResponse(user);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario"));
        List<UserRolEntity> users = userRolRepository.findAllByUser(user);
        if (!users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El usuario tiene roles asignados y no puede ser eliminado.");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserDetailedResponse editUserById(Long id, UpdateUserRequest dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario"));
        UserDetailEntity userDetail = userDetailRepository.findByUser(user);
        return userMapper.toUserDetailedResponse(userRepository.save(userMapper.userUpdate(dto, user, userDetail)));
    }
}
