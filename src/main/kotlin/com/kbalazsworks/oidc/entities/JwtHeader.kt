package com.kbalazsworks.oidc.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class JwtHeader(
    @JsonProperty("alg")
    val alg: String,
    @JsonProperty("kid")
    val kid: String,
    @JsonProperty("typ")
    val typ: String,
)
