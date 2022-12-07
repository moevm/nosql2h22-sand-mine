package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.ExportDTO
import com.leti.sand_mine.DTO.ImportDTO
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.Properties

@RestController
class ImportExportController {

    private val driver: Driver

    init {
        val properties = javaClass.classLoader.getResourceAsStream("application.properties").use {
            Properties().apply { load(it) }
        }
        val authTokens = AuthTokens.basic(properties.getProperty(usernameKey), properties.getProperty(passwordKey))
        driver = GraphDatabase.driver(
            "bolt://neo4j:7687",
            authTokens
        )
    }

    private val importPath: String by lazy {
        driver.session().use {
            it.beginTransaction().use { tx ->
                val result = tx.run(
                    "CALL dbms.listConfig() YIELD name, value " +
                            "WHERE name='dbms.directories.import'" +
                            "RETURN value"
                )
                return@lazy result.next().get("value").asString()
            }
        }
    }

    @GetMapping("/export")
    fun exportDatabase(): ExportDTO {
        val fileName = "graph"
        driver.session().use {
            it.beginTransaction().use { tx ->
                tx.run(
                    "CALL apoc.export.graphml.all('$fileName', " +
                            "{batchSize: 10000, readLabels: true, storeNodeIds: false, useTypes:true})"
                )
                tx.commit()
            }
        }

        return ExportDTO(File("$importPath/$fileName").bufferedReader().use {
            it.readText()
        })
    }


    @PostMapping("/import")
    fun importDatabase(@RequestBody importDto: ImportDTO) {
        File("$importPath/${importDto.fileName}").printWriter().use { out ->
            out.println(importDto.data)
        }
        driver.session().use {
            it.beginTransaction().use { tx ->
                tx.run(
                    "CALL apoc.periodic.iterate('MATCH (n) RETURN n', 'DETACH DELETE n', {batchSize:1000})"
                )
                tx.run(
                    "CALL apoc.import.graphml('${importDto.fileName}', " +
                            "{batchSize: 10000, readLabels: true, storeNodeIds: false, useTypes:true})"
                )
                tx.commit()
            }
        }
    }

    @PostMapping("/clear")
    fun clearDatabase() {
        driver.session().use {
            it.beginTransaction().use { tx ->
                tx.run(
                    "CALL apoc.periodic.iterate('MATCH (n) RETURN n', 'DETACH DELETE n', {batchSize:1000})"
                )
                tx.commit()
            }
        }
    }


    companion object {
        const val usernameKey = "spring.neo4j.authentication.username"
        const val passwordKey = "spring.neo4j.authentication.password"
    }
}