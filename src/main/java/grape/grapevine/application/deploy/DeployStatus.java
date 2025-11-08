package grape.grapevine.application.deploy;

public enum DeployStatus {
    CREATED(201, "완료"),
    FORBIDDEN(403, "실패"),
    PROCESSING(0, "진행중");

    final int code;
    final String description;

    DeployStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
