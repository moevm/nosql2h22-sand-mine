package com.leti.sand_mine.config

import com.leti.sand_mine.converters.StringValueToDateTimeValueConverter
import com.leti.sand_mine.converters.StringValueToDateValueConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.data.neo4j.core.convert.Neo4jConversions

@Configuration
class ConverterConfig {

    @Bean
    fun neo4jConversions(): Neo4jConversions? {
        val additionalConverters: Set<GenericConverter?> = setOf(StringValueToDateValueConverter(), StringValueToDateTimeValueConverter())
        return Neo4jConversions(additionalConverters)
    }
}