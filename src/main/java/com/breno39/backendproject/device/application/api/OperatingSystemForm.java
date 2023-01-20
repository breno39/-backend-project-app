package com.breno39.backendproject.device.application.api;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class OperatingSystemForm {

    @NotBlank
    private UUID Id;
}
