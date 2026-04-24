package com.hannah.applyflow.job;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Current status of the job application")
public enum JobStatus {

    @Schema(description = "Application submitted, waiting for response")
    APPLIED,

    @Schema(description = "Application successfully passed the document review stage")
    DOCUMENT_PASSED,

    @Schema(description = "Currently in the interview process")
    INTERVIEW,

    @Schema(description = "Received a formal job offer/contract")
    OFFER,

    @Schema(description = "Application rejected by the company")
    REJECTED
}
