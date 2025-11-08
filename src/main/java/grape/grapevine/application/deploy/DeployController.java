package grape.grapevine.application.deploy;

import grape.grapevine.application.deploy.dto.DispatchReq;
import grape.grapevine.application.deploy.dto.DispatchRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import grape.grapevine.application.deploy.dto.DeployRes;
import grape.grapevine.global.BaseResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Deploy", description = "배포 관련 API")
@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@Validated
@Tag(name = "deploy", description = "배포 관련 API")
public class DeployController {

    private final DeployService deployService;

    @Operation(summary = "GitHub Actions 워크플로우 디스패치", description = "GitHub Actions 워크플로우를 수동으로 트리거합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "워크플로우 디스패치 성공",
                    content = @Content(schema = @Schema(implementation = DispatchRes.class)))
    })
    @PostMapping("/dispatch")
    public ResponseEntity<DispatchRes> dispatchWorkflow(@RequestBody DispatchReq request) {
        DispatchRes response = deployService.triggerWorkflow(request);
        return ResponseEntity.ok(response);
    }
}
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
