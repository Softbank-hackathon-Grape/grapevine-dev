package grape.grapevine.application.auth;

import grape.grapevine.application.auth.dto.LoginReq;
import grape.grapevine.application.auth.dto.LoginRes;
import grape.grapevine.application.user.User;
import grape.grapevine.application.user.UserRepository;
import grape.grapevine.global.BusinessException;
import grape.grapevine.global.ErrorCode;
import grape.grapevine.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public LoginRes login(LoginReq req) {
    User user = userRepository.findByUserId(req.userId())
        .orElseThrow(() -> new BusinessException(ErrorCode.LOGIN_FAIL));

    if (!passwordEncoder.matches(req.password(), user.getPwd())) {
      throw new BusinessException(ErrorCode.LOGIN_FAIL);
    }

    String token = jwtTokenProvider.createAccessToken(user);

    return LoginRes.builder()
        .userId(user.getUserId())
        .accessToken(token)
        .build();
  }
}
