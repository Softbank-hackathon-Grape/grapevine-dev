package grape.grapevine.application.auth;

import grape.grapevine.application.auth.dto.LoginReq;
import grape.grapevine.application.auth.dto.LoginRes;
import grape.grapevine.application.user.User;
import grape.grapevine.application.user.UserRepository;
import grape.grapevine.global.BusinessException;
import grape.grapevine.global.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String TEST_USER_ID = "grape";
    private static final String TEST_PASSWORD = "password";

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .userId(TEST_USER_ID)
                .pwd(passwordEncoder.encode(TEST_PASSWORD))
                .build();
        userRepository.save(user);
    }
    @Test
    @DisplayName("로그인 성공")
    void login_success() {
        // given
        LoginReq loginReq = new LoginReq(TEST_USER_ID, TEST_PASSWORD);

        // when
        LoginRes loginRes = authService.login(loginReq);

        // then
        assertThat(loginRes).isNotNull();
        assertThat(loginRes.userId()).isEqualTo(TEST_USER_ID);
        assertThat(loginRes.accessToken()).isNotBlank();
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void login_fail_wrong_password() {
        // given
        LoginReq loginReq = new LoginReq(TEST_USER_ID, "wrongpassword");

        // when & then
        BusinessException exception = assertThrows(BusinessException.class, () -> authService.login(loginReq));
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.LOGIN_FAIL);
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지 않는 사용자")
    void login_fail_user_not_found() {
        // given
        LoginReq loginReq = new LoginReq("nonexistentuser", "password");

        // when & then
        BusinessException exception = assertThrows(BusinessException.class, () -> authService.login(loginReq));
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.LOGIN_FAIL);
    }
}
