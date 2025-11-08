package grape.grapevine.application.deploy;

import grape.grapevine.application.deploy.dto.DeployRes;
import grape.grapevine.global.BusinessException;
import grape.grapevine.global.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeployService {
    private final DeployRepository deployRepository;

    public DeployRes getDeploy(long id) {
        Deploy deploy = deployRepository.findById(id)
            .orElseThrow(()-> new BusinessException(ErrorCode.NO_EXIST_VALUE, "DB에 배포 데이터가 존재하지 않습니다."));

        return DeployRes.from(deploy);
    }

    public String getDeployStatus(long id) {
        Deploy deploy = deployRepository.findById(id)
            .orElseThrow(()-> new BusinessException(ErrorCode.NO_EXIST_VALUE, "DB에 배포 데이터가 존재하지 않습니다."));

        return deploy.getDeployStatus().getDescription();
    }
}
