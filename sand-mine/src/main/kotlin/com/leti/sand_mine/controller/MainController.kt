package com.leti.sand_mine.controller

import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class MainController(private val zoneRepository: ZoneRepository,) {
    @GetMapping
    fun main(model: Model): String {
        val zones = zoneRepository.findAll();
        model.addAttribute("zones",zones);
        return "index"
    }

    @GetMapping("/*")//Для того чтобы не было ошибки 404 в консоли
    fun allRequests(): String {
        return "forward:/"
    }
}