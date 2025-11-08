package grape.grapevine.application.deploy;

import grape.grapevine.application.deploy.dto.DispatchReq;
import grape.grapevine.application.deploy.dto.DispatchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@Validated
public class DeployController {

    private final DeployService deployService;

    @PostMapping("/dispatch")
    public ResponseEntity<DispatchRes> dispatchWorkflow(@RequestBody DispatchReq request) {
        DispatchRes response = deployService.triggerWorkflow(request);
        return ResponseEntity.ok(response);
    }
}