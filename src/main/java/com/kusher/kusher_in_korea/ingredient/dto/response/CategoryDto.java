package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long CategoryId;
    private String categoryName;
    public CategoryDto(Category category) {
        this.CategoryId = category.getId();
        this.categoryName = category.getName();
    }
}
