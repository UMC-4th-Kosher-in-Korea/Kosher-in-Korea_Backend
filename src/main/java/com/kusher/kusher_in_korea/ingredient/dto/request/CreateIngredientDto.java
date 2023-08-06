package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateIngredientDto {
    private String ingredientName;
    private String ingredientCategory;
    private MultipartFile ingredientImage; // This is the image file that will be uploaded to S3
    private String ingredientDescription;
    private int ingredientPrice;
}
