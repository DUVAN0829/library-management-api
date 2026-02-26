package co.duvan.loan.infrastructure.adapters.output;

import co.duvan.loan.application.ports.output.UserClientPort;
import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.infrastructure.adapters.output.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientAdapter implements UserClientPort {

    private final UserFeignClient userFeignClient;

    @Override
    public UserClientResponse findUserById(Long userId, String token) {
        return userFeignClient.findById(userId);
    }

}
