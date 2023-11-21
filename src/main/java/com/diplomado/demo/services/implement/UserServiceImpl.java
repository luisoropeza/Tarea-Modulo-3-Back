package com.diplomado.demo.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.diplomado.demo.dtos.UserDetailedDto;
import com.diplomado.demo.dtos.UserDto;
import com.diplomado.demo.dtos.Request.CreateUserWithRolesRequest;
import com.diplomado.demo.dtos.Request.UpdateUserRequest;
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

    public List<UserDto> getAllUsersNoDetailed() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    public List<UserDetailedDto> getAllUsersDetailed() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserDetailedDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailedDto createUser(CreateUserWithRolesRequest dto) {
        UserEntity user = new UserEntity();
        if (dto.getFirstName() != null && dto.getLastName() != null) {
            user = userRepository.save(userMapper.toUserEntity(dto));
        } else {
            user = userRepository.save(userMapper.toUserEntity(userMapper.toUserDto(dto)));
        }
        if (dto.getRolesId() != null) {
            userRolService.assignRolesToUser(user.getId(), dto.getRolesId());
        }
        return userMapper.toUserDetailedDto(user);
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
    public UserDetailedDto editUserById(Long id, UpdateUserRequest dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario"));
        UserDetailEntity userDetail = userDetailRepository.findByUser(user);
        return userMapper.toUserDetailedDto(userRepository.save(userMapper.userUpdate(dto, user, userDetail)));
    }
}
