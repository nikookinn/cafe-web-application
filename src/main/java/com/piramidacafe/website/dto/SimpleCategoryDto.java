
package com.piramidacafe.website.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCategoryDto {

    @NotNull(message = "Cannot be empty!")
    private Long categoryId;

    private String name;
}
