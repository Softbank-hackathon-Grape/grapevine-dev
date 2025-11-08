package grape.grapevine.application.auth;

import grape.grapevine.application.auth.dto.LoginReq;
import grape.grapevine.application.auth.dto.LoginRes;
import grape.grapevine.global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public BaseResponse<LoginRes> login(@RequestBody LoginReq req) {
    return BaseResponse.success(authService.login(req));
  }
}
