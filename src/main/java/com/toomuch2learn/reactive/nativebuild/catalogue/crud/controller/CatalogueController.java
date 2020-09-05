package com.toomuch2learn.reactive.nativebuild.catalogue.crud.controller;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.CatalogueItemGenerator;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.CatalogueItem;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.ResourceIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping(CatalogueControllerAPIPaths.BASE_PATH)
public class CatalogueController {

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItems() {
        return Flux.fromIterable(CatalogueItemGenerator.generateCatalogueItemsList());
    }

    @GetMapping(path= CatalogueControllerAPIPaths.GET_ITEMS_STREAM, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItemsStream() {
        return Flux.fromIterable(CatalogueItemGenerator.generateCatalogueItemsList())
                .delayElements(Duration.ofMillis(200));
    }

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEM)
    public Mono<CatalogueItem> getCatalogueItemBySKU(@PathVariable(value = "sku") String skuNumber) {
        return Mono.just(CatalogueItemGenerator.generateCatalogueItem());
    }

    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> addCatalogueItem(@Valid @RequestBody CatalogueItem catalogueItem) {

        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(new ResourceIdentity(10l))).cast(ResponseEntity.class);
    }

    @PutMapping(CatalogueControllerAPIPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCatalogueItem(
            @PathVariable(value = "sku") String skuNumber,
            @Valid @RequestBody CatalogueItem catalogueItem) {

    }

    @DeleteMapping(CatalogueControllerAPIPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCatalogItem(@PathVariable(value = "sku") String skuNumber) {

    }
}
