package grape.grapevine.application.deploy;

public enum DeployStatus {
    CREATED(201),
    FORBIDDEN(403),
    PROCESSING(0);

    final int code;

    DeployStatus(int code) {this.code = code;}
}
