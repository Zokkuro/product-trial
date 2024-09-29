package com.back.producttrial.dto;

import com.back.producttrial.enumeration.InventoryReference;
import com.back.producttrial.validator.InventoryReferencePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    public interface OnPost{}
    public interface OnPatch{}

    @Schema(description = "Identifiant du produit", example = "1")
    private Long id;

    @NotBlank(groups = OnPost.class)
    @Schema(description = "Code du produit", example = "S-0001")
    private String code;

    @NotBlank(groups = OnPost.class)
    @Schema(description = "Nom du produit", example = "Iphone")
    private String name;

    @Schema(description = "Description du produit" , example = "Voici le nouvelle IPhone")
    private String description;

    @Schema(description = "Url de l'image lié au produit")
    private String image;

    @NotBlank(groups = OnPost.class)
    @Schema(description = "Catégorie du produit" , example = "Smartphone")
    private String category;

    @NotNull(groups = OnPost.class)
    @Schema(description = "Prix du produit" , example = "900.00")
    private BigDecimal price;

    @Schema(description = "Référence interne du produit", example = "INTERNAL-0001")
    private String internalReference;

    @Schema(description = "Identifiant shell du produit")
    private Long shellId;

    @NotNull(groups = OnPost.class)
    @InventoryReferencePattern(groups = {OnPost.class, OnPatch.class})
    @Schema(description = "Etat d'inventaire du produit",allowableValues = {"INSTOCK","LOWSTOCK","OUTOFSTOCK"}, example = "INSTOCK")
    private String inventoryReference;

    @Max(value = 5, groups = {OnPost.class, OnPatch.class})
    @Min(value = 0, groups = {OnPost.class, OnPatch.class})
    @Schema(description = "Note du produit allant de 1 à 5", example = "5")
    private Integer rating;

    @NotNull(groups = OnPost.class)
    @Schema(description = "Date de création du produit", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @NotNull(groups = OnPatch.class)
    @Schema(description = "Date de dernière mise à jour du produit", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public InventoryReference getInventoryReference() {
        return InventoryReference.valueOf(inventoryReference);
    }
}
