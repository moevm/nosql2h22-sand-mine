package com.leti.sand_mine.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/temp")
class MainController {
    @GetMapping
    fun main():String{
        return "index"
    }
}