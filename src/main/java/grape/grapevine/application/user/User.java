package grape.grapevine.application.user;

import grape.grapevine.application.common.BaseEntity;
import grape.grapevine.application.deploy.Deploy;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", nullable = false, columnDefinition = "BIGINT")
    private Long userIdx;

    @NotNull
    @Column(name = "id", nullable = false, columnDefinition = "VARCHAR(150)")
    private String id;

    @NotNull
    @Column(name = "pwd", nullable = false, columnDefinition = "VARCHAR(150)")
    private String pwd;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deploy> deployList = new ArrayList<>();

    // === 편의 메서드 ===
    public void addDeploy(Deploy deploy) {
        deployList.add(deploy);
    }

    public void updatePwd(String newPwd) {
        this.pwd = newPwd;
    }
}
