package grape.grapevine.application.deploy.dto;

import grape.grapevine.application.deploy.Deploy;
import grape.grapevine.application.deploy.DeployStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
@Schema(description = "배포 응답 DTO")
public record DeployRes(
    Long id,
    Long userId,
    DeployStatus deployStatus,
    String deployDesc,
    String deploySetting,
    String deployInfo,
    String monitorUrl,
    String projectUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static DeployRes from(Deploy deploy) {
        return new DeployRes(
            deploy.getId(),
            deploy.getUser().getUserIdx(),
            deploy.getDeployStatus(),
            deploy.getDeployDesc(),
            deploy.getDeploySetting(),
            deploy.getDeployInfo(),
            deploy.getMonitorUrl(),
            deploy.getProjectUrl(),
            deploy.getCreatedAt(),
            deploy.getUpdatedAt()
        );
    }
}
