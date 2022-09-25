package ru.ktey.testneo4j.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.ktey.testneo4j.repository.MessageRepo

@Controller
@RequestMapping("/")
class MainController(
    private val messageRepo:MessageRepo
) {
    @GetMapping
    fun messages(model: Model):String{
        model.addAttribute("messages",messageRepo.findAll());
        return "index";
    }
}