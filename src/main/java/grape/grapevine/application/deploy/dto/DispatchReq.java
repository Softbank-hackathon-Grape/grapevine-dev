package grape.grapevine.application.deploy.dto;

import java.util.Map;

public record DispatchReq(
        Map<String, Object> inputs,
        String actor
) {
}
