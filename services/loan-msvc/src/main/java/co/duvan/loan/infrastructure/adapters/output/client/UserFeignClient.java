package co.duvan.loan.infrastructure.adapters.output.client;

import co.duvan.loan.application.ports.output.dto.UserClientResponse;
import co.duvan.loan.infrastructure.feign.config.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-msvc", url = "http://localhost:8084", configuration = FeignClientInterceptor.class)
public interface UserFeignClient {

    @GetMapping("/users/api/v1/{id}")
    UserClientResponse findById(@PathVariable Long id);

}
