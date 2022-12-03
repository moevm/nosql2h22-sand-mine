package com.leti.sand_mine.converters

import org.neo4j.driver.internal.value.DateValue
import org.neo4j.driver.internal.value.StringValue
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

class StringValueToDateValueConverter: GenericConverter {
    override fun getConvertibleTypes(): Set<GenericConverter.ConvertiblePair> {
        val convertiblePairs: MutableSet<GenericConverter.ConvertiblePair> = HashSet()
        convertiblePairs.add(GenericConverter.ConvertiblePair(StringValue::class.java, DateValue::class.java))
        return convertiblePairs
    }

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        return if (StringValue::class.java.isAssignableFrom(sourceType.type)) {
            var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            formatter = formatter.withLocale(Locale.getDefault())
            val date = LocalDate.parse((source as StringValue).asString(), formatter)
            return DateValue(date)
        } else null
    }
}