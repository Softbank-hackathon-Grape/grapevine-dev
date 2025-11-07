package application.deploy;

import global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deploy")
@RequiredArgsConstructor
@Validated
public class DeployController {
    private final DeployService deployService;

    @GetMapping("/test")
    public BaseResponse<String> test() {
        return BaseResponse.success("test success");
    }

}
