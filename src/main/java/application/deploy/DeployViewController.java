package application.deploy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DeployViewController {

    @GetMapping
    public String getDeployMainPage() {
        return "/"; // 뷰 이름 반환
    }
}
