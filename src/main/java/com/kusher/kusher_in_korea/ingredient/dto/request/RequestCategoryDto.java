package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCategoryDto { // 신규 식재료 카테고리 생성 기능
    private String categoryName;
}
