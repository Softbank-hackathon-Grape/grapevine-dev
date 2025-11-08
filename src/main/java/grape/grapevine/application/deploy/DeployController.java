package grape.grapevine.application.deploy;

import grape.grapevine.application.deploy.dto.DeployRes;
import grape.grapevine.global.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deploy")
@RequiredArgsConstructor
@Validated
@Tag(name = "deploy", description = "배포 관련 API")
public class DeployController {
    private final DeployService deployService;

    @Operation(summary = "배포 상세 조회 API", description = "배포 상세 조회 API 입니다.")
    @GetMapping("/{id}")
    public BaseResponse<DeployRes> getDeploy(@PathVariable("id") @NotNull Long id) {
        return BaseResponse.success(deployService.getDeploy(id));
    }

    @Operation(summary = "배포 상태 조회 API", description = "배포 상태 조회 API 입니다.")
    @GetMapping("/status/{id}")
    public BaseResponse<String> getDeployStatus(@PathVariable("id") @NotNull Long id) {
        return BaseResponse.success(deployService.getDeployStatus(id));
    }
}
