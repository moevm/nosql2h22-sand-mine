package ru.ktey.testneo4j.repository

import org.springframework.data.repository.CrudRepository
import ru.ktey.testneo4j.domain.Message
import java.util.*


interface MessageRepo : CrudRepository<Message,Long>