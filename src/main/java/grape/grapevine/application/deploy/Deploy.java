package grape.grapevine.application.deploy;

import grape.grapevine.application.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "deploy_id", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
}
