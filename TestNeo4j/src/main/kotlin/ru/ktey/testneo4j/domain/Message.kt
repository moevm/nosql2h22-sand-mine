package ru.ktey.testneo4j.domain

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.EqualsAndHashCode
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.GeneratedValue.UUIDGenerator
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.support.UUIDStringGenerator
import java.time.LocalDateTime

@Node
@EqualsAndHashCode
class Message(
    @Id
    @GeneratedValue
    var id:Long? = null,
    @Property(name = "text") var text:String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Property(name = "creationDate") var creationDate:LocalDateTime?
) {
}