package co.duvan.loan.application.ports.output;

import co.duvan.loan.application.ports.output.dto.UserClientResponse;

public interface UserClientPort {

    UserClientResponse findUserById(Long userId, String token);

    Long findUserIdByKeycloakId(String keycloakId);

}
