package com.toomuch2learn.reactive.nativebuild.catalogue.crud.controller;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.exception.ResourceNotFoundException;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.CatalogueItem;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.ResourceIdentity;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.service.CatalogueCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CatalogueCrudService catalogueCrudService;

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItems() {
        return catalogueCrudService.getCatalogueItems();
    }

    @GetMapping(path= CatalogueControllerAPIPaths.GET_ITEMS_STREAM, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItemsStream() {
        return catalogueCrudService
            .getCatalogueItems()
            .delayElements(Duration.ofMillis(200));
    }

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEM)
    public Mono<CatalogueItem> getCatalogueItemBySKU(@PathVariable(value = "sku") String skuNumber) throws ResourceNotFoundException {
        return catalogueCrudService.getCatalogueItem(skuNumber);
    }

    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> addCatalogueItem(@Valid @RequestBody CatalogueItem catalogueItem) {

        Mono<Long> id = catalogueCrudService.addCatalogItem(catalogueItem);

        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new ResourceIdentity(value))).cast(ResponseEntity.class);
    }

    @PutMapping(CatalogueControllerAPIPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCatalogueItem(
            @PathVariable(value = "sku") String skuNumber,
            @Valid @RequestBody CatalogueItem catalogueItem) throws ResourceNotFoundException {
        catalogueCrudService.updateCatalogueItem(catalogueItem);
    }

    @DeleteMapping(CatalogueControllerAPIPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCatalogItem(@PathVariable(value = "sku") String skuNumber) throws ResourceNotFoundException {
        Mono<CatalogueItem> catalogueItem = catalogueCrudService.getCatalogueItem(skuNumber);
        catalogueItem.subscribe(
                value -> {
                    catalogueCrudService.deleteCatalogueItem(value);
                }
        );
    }
}
