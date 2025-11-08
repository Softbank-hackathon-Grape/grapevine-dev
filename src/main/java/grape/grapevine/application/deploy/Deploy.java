package grape.grapevine.application.deploy;

import grape.grapevine.application.common.BaseEntity;
import grape.grapevine.application.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deploy")
public class Deploy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deploy_idx", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @NotNull
    @Column(name = "deploy_status", nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DeployStatus deployStatus = DeployStatus.PROCESSING;

    @Column(name = "deploy_desc", columnDefinition = "TEXT")
    private String deployDesc;

    @NotNull
    @Column(name = "deploy_setting", nullable = false, columnDefinition = "TEXT")
    private String deploySetting;

    @NotNull
    @Column(name = "deploy_info", nullable = false, columnDefinition = "TEXT")
    private String deployInfo;

    @Column(name = "monitor_url", columnDefinition = "TEXT")
    private String monitorUrl;

    @NotNull
    @Column(name = "project_url", nullable = false, columnDefinition = "TEXT")
    private String projectUrl;

    // === 업데이트 메서드 ===
    public void update(String deployDesc, String deploySetting, String deployInfo, String monitorUrl, String projectUrl) {
        this.deployDesc = deployDesc;
        this.deploySetting = deploySetting;
        this.deployInfo = deployInfo;
        this.monitorUrl = monitorUrl;
        this.projectUrl = projectUrl;
    }

    public void updateStatus(DeployStatus status) {
        this.deployStatus = status;
    }
}
