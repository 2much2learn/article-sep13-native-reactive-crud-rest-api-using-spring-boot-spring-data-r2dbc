package com.toomuch2learn.reactive.nativebuild.catalogue.crud.service;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.CatalogueItemGenerator;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.event.CatalogueItemEvent;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.exception.ResourceNotFoundException;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.CatalogueItem;
import com.toomuch2learn.reactive.nativebuild.catalogue.crud.repository.CatalogueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Service class to handle Catalogue Item CRUD Operations. Upon Creating/Updating CatalogueItem, CatalogueItemEvent will
 * be published to applicationEventPublisher
 *
 * @author Madan Narra
 */
@Slf4j
@Service
public class CatalogueCrudService {

    private final ApplicationEventPublisher publisher;
    private final CatalogueRepository catalogueRepository;

    CatalogueCrudService(ApplicationEventPublisher publisher, CatalogueRepository catalogueRepository) {
        this.publisher = publisher;
        this.catalogueRepository = catalogueRepository;
    }

    public Flux<CatalogueItem> getCatalogueItems() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        return catalogueRepository.findAll(sort);
    }

    public Mono<CatalogueItem> getCatalogueItem( String skuNumber) throws ResourceNotFoundException {
        return getCatalogueItemBySku(skuNumber);
    }

    public Mono<Long> addCatalogItem(CatalogueItem catalogueItem) {
        catalogueItem.setCreatedOn(Instant.now());

        return Mono.just(10L);
    }

    public void updateCatalogueItem(CatalogueItem catalogueItem) throws ResourceNotFoundException{
        // Do Nothing
    }

    public void deleteCatalogueItem(CatalogueItem catalogueItem) {
        // Do Nothing
    }

    private Mono<CatalogueItem> getCatalogueItemBySku(String skuNumber) throws ResourceNotFoundException {
        return Mono.just(CatalogueItemGenerator.generateCatalogueItem());
    }

    private final void publishCatalogueItemEvent(String eventType, CatalogueItem item) {
        this.publisher.publishEvent(new CatalogueItemEvent(eventType, item));
    }
}