package ru.ktey.testneo4j.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ktey.testneo4j.domain.Message
import ru.ktey.testneo4j.exception.NotFoundException
import ru.ktey.testneo4j.repository.MessageRepo
import java.time.LocalDateTime

@RestController
@RequestMapping("api/messages")
class MessageController(
    private val messageRepo:MessageRepo
) {

    @GetMapping
    fun getAll():List<Message>{
        return messageRepo.findAll().toList()
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Long):Message{
        return messageRepo.findById(id).orElseThrow { NotFoundException() }
    }

    @PostMapping
    fun addOneMessage(@RequestBody message:Message): Message {
        message.creationDate = LocalDateTime.now()
        return messageRepo.save(message)
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id:Long){
        messageRepo.deleteById(id)
    }

}