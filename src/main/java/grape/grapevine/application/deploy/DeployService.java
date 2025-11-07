package grape.grapevine.application.deploy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeployService {
    private final DeployRepository deployRepository;
}
