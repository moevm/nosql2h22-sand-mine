package com.leti.sand_mine.controller

import com.leti.sand_mine.repository.WorkerRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/worker")
class WorkerController(
    private val workerRepository: WorkerRepository
) {
}