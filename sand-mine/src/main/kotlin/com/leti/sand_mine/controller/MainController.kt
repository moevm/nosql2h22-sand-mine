package com.leti.sand_mine.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class MainController {
    @GetMapping
    fun main():String{
        return "index"
    }


    @GetMapping("/*")//Для того чтобы не было ошибки 404 в консоли
    fun allRequests():String{
        return "forward:/"
    }
}