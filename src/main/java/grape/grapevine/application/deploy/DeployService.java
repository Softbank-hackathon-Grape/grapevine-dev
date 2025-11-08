package grape.grapevine.application.deploy;

import grape.grapevine.application.deploy.dto.DispatchReq;
import grape.grapevine.application.deploy.dto.DispatchRes;
import grape.grapevine.client.githubclient.GithubClient;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeployService {

    private final GithubClient githubClient;

    public DispatchRes triggerWorkflow(DispatchReq request) {
        log.info("Attempting to dispatch workflow for {}/{}. Workflow URL: https://github.com/{}/{}/actions",
                GithubClient.OWNER, GithubClient.REPO, GithubClient.OWNER, GithubClient.REPO);

        /*
          Terraform 환경변수를 GitHub Actions에서 다음과 같이 매핑하도록 주석으로 명시:
          env:
            TF_VAR_env: ${{ github.event.inputs.env }}
            TF_VAR_service_name: ${{ github.event.inputs.service_name }}
            TF_VAR_image_tag: ${{ github.event.inputs.image_tag }}
        */
        githubClient.dispatchWorkflow(
                request.inputs()
        );

        log.info("Successfully dispatched workflow for repository: {}/{}", GithubClient.OWNER, GithubClient.REPO);

        return new DispatchRes(
                "Workflow dispatched successfully",
                ZonedDateTime.now(ZoneId.of("UTC")),
                GithubClient.REF
        );
    }
}