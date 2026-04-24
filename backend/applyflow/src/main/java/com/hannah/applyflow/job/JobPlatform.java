package com.hannah.applyflow.job;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Platform used to find or apply for the job")
public enum JobPlatform {

    @Schema(description = "LinkedIn (Global)")
    LINKEDIN,

    @Schema(description = "Indeed (Global)")
    INDEED,

    @Schema(description = "XING (DACH region focus)")
    XING,

    @Schema(description = "StepStone (Germany)")
    STEPSTONE,

    @Schema(description = "Company career page")
    DIRECT_APPLY,

    @Schema(description = "Other sources")
    OTHER
}
