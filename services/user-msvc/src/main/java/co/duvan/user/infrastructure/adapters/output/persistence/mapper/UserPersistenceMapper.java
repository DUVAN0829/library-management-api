package co.duvan.user.infrastructure.adapters.output.persistence.mapper;

import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

    List<User> toListUser(List<UserEntity> userEntityList);

}
