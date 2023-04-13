package com.example.flywaydemo.web.mapper;

import com.example.flywaydemo.data.model.UserEntity;
import com.example.flywaydemo.web.resource.UserGetResource;
import com.example.flywaydemo.web.resource.UserPostResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public List<UserGetResource> mapToUserGetResourceList(List<UserEntity> userEntities) {

        return userEntities.stream().map(userEntity -> mapToUserGetResource(userEntity)).toList();

    }

    public UserGetResource mapToUserGetResource(UserEntity userEntity) {

        return UserGetResource.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .birthDate(userEntity.getBirthDate())
                .build();

    }

    public UserEntity mapToUserEntity(UserPostResource userPostResource) {
        return UserEntity.builder()
                .firstName(userPostResource.getFirstName())
                .lastName(userPostResource.getLastName())
                .birthDate(userPostResource.getBirthDate())
                .build();
    }
}
