package grape.grapevine.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import grape.grapevine.global.BaseResponse;
import grape.grapevine.global.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {

    final ErrorCode errorCode = ErrorCode.HANDLE_ACCESS_DENIED;

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    BaseResponse<Object> errorResponse = BaseResponse.fail(errorCode);

    objectMapper.writeValue(response.getWriter(), errorResponse);
  }
}