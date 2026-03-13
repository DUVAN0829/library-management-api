package co.duvan.copy.infrastructure.observability;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletResponse instanceof HttpServletResponse httpServletResponse) {
            String traceId = Span.current().getSpanContext().getTraceId();
            log.info("TraceId{}", traceId);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}