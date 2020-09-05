package com.toomuch2learn.reactive.nativebuild.catalogue.crud.model;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.validation.IEnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
public class CatalogueItem {

    private Long id;

    @NotEmpty(message = "SKU cannot be null or empty")
    @NonNull
    private String sku;

    @NotEmpty(message = "Name cannot be null or empty")
    @NonNull
    private String name;

    @NotEmpty(message = "Description cannot be null or empty")
    @NonNull
    private String description;

    @NonNull
    @IEnumValidator(
        enumClazz = com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.Category.class,
        message = "Invalid category provided"
    )
    private String category;

    @NotNull(message = "Price cannot be null or empty")
    @NonNull
    private Double price;

    @NotNull(message = "Inventory cannot be null or empty")
    @NonNull
    private Integer inventory;

    @NonNull
    private Instant createdOn;

    private Instant updatedOn;
}
