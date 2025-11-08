package grape.grapevine.application.deploy;

import grape.grapevine.global.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deploy")
@RequiredArgsConstructor
@Validated
@Tag(name = "deploy", description = "배포 관련 API")
public class DeployController {
    private final DeployService deployService;

    @Operation(summary = "test API", description = "테스트 API 입니다.")
    @GetMapping("/test")
    public BaseResponse<String> test() {
        return BaseResponse.success("test success");
    }

}
