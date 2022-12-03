package com.leti.sand_mine.converters

import org.neo4j.driver.internal.value.DateTimeValue
import org.neo4j.driver.internal.value.StringValue
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class StringValueToDateTimeValueConverter: GenericConverter {

    override fun getConvertibleTypes(): Set<GenericConverter.ConvertiblePair> {
        val convertiblePairs: MutableSet<GenericConverter.ConvertiblePair> = HashSet()
        convertiblePairs.add(GenericConverter.ConvertiblePair(StringValue::class.java, DateTimeValue::class.java))
        return convertiblePairs
    }

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        return if (StringValue::class.java.isAssignableFrom(sourceType.type)) {
            val localDateTime = LocalDateTime.parse((source as StringValue).asString().takeWhile { it != '+'})
            val zoneDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
            return DateTimeValue(zoneDateTime)
        } else null
    }
}