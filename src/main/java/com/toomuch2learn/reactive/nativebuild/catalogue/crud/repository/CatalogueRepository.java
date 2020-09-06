package com.toomuch2learn.reactive.nativebuild.catalogue.crud.repository;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.CatalogueItem;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CatalogueRepository {

    Mono<CatalogueItem> findBySku(String sku) {
        return Mono.just(new CatalogueItem());
    }
}
