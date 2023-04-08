package cc.forim.armagin.server.infra.support;

import lombok.NonNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * TraceId提供器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/31 14:22
 */

@Component
public class MappedDiagnosticContextProvider {

    public void process(@NonNull Runnable runnable) {
        String uuid = UUID.randomUUID().toString();
        MDC.put("TRACE_ID", uuid);
        try {
            runnable.run();
        } finally {
            MDC.remove("TRACE_ID");
        }
    }
}