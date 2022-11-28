package com.leti.sand_mine.DTO

import org.springframework.http.HttpStatus

data class AuthResponseDto(
    val workerId: Long,
    val workerType: String
) {
}