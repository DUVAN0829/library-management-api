package co.duvan.user.infrastructure.adapters.input.rest.mapper;

import co.duvan.user.domain.model.User;
import co.duvan.user.infrastructure.adapters.input.rest.model.request.UserRequest;
import co.duvan.user.infrastructure.adapters.input.rest.model.response.UserResponse;
import co.duvan.user.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserPersistenceMapper.class})
public interface UserRestMapper {

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> userList);

}
