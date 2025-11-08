package grape.grapevine.client.githubclient;

import grape.grapevine.client.ApiClient;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GithubClient extends ApiClient {

    private final String token;
    private final String baseUrl;

    public static final String OWNER = "Softbank-hackathon-Grape";
    public static final String REPO = "deploy-project";
    public static final String WORKFLOW_ID = "deploy.yml";
    public static final String REF = "main";

    public GithubClient(@Qualifier("GithubRestClient") RestClient restClient,
                        @Value("${github.token}") String token,
                        @Value("${github.base-url:https://api.github.com}") String baseUrl) {
        super(restClient);
        this.token = token;
        this.baseUrl = baseUrl;
    }

    public void dispatchWorkflow(Map<String, Object> inputs) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/repos/{owner}/{repo}/actions/workflows/{workflowId}/dispatches")
                .buildAndExpand(OWNER, REPO, WORKFLOW_ID)
                .toUri();

        Map<String, Object> body = Map.of(
                "ref", REF,
                "inputs", inputs != null ? inputs : Map.of()
        );

        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + token,
                "Accept", "application/vnd.github.v3+json",
                "X-GitHub-Api-Version", "2022-11-28"
        );

        post(uri, headers, body, Void.class);
    }
}
