package grape.grapevine.application.deploy;

import grape.grapevine.global.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deploy")
@RequiredArgsConstructor
@Validated
@Tag(name = "deploy", description = "배포 관련 API")
public class DeployController {
    private final DeployService deployService;

    @Operation(summary = "배포 시작 API", description = "배포 트리거 API 입니다.")
    @PostMapping
    public BaseResponse<String> deploy() {
        return BaseResponse.success("success");
    }

    @Operation(summary = "배포 상태 조회 API", description = "배포 상태 조회 API 입니다.")
    @GetMapping("/status")
    public BaseResponse<String> getDeployStatus() {
        return BaseResponse.success("success");
    }

}
