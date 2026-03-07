package co.duvan.loan.infrastructure.adapters.output.client;

import co.duvan.loan.application.ports.output.dto.CopyClientResponse;
import co.duvan.loan.infrastructure.feign.config.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "copy-msvc", url = "${copy-msvc.url}", configuration = FeignClientInterceptor.class)
public interface CopyFeignClient {

    @GetMapping("/copies/api/v1/{id}")
    CopyClientResponse findById(@PathVariable Long id);

}
