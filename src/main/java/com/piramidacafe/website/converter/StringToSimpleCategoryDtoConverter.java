package com.piramidacafe.website.converter;

import com.piramidacafe.website.dto.SimpleCategoryDto;
import org.springframework.core.convert.converter.Converter;

public class StringToSimpleCategoryDtoConverter implements Converter<String, SimpleCategoryDto> {
    @Override
    public SimpleCategoryDto convert(String source) {
        SimpleCategoryDto dto = new SimpleCategoryDto();
        dto.setCategoryId(Long.valueOf(source));
        return dto;
    }
}
