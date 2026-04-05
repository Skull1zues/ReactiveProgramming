package com.soumya._stWebFluxApplication.sec08.dto;

import java.util.UUID;

public record UploadResponse(UUID confirmationId, Long productCount) {
}
