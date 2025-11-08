package grape.grapevine.application.auth.dto;

import lombok.Builder;

@Builder
public record LoginRes(
    String userId,
    String accessToken
) {}