package com.toomuch2learn.reactive.nativebuild.catalogue.crud.hints;

import org.springframework.graalvm.extension.NativeImageConfiguration;
import org.springframework.graalvm.extension.NativeImageHint;
import org.springframework.graalvm.extension.TypeInfo;
import org.springframework.graalvm.type.AccessBits;

/**
 * https://github.com/spring-projects-experimental/spring-graalvm-native/blob/master/spring-graalvm-native-feature/src/main/java/org/springframework/graalvm/extension/NativeImageHint.java


@NativeImageHint(typeInfos = {
    @TypeInfo(typeNames = {
        "com.toomuch2learn.reactive.nativebuild.catalogue.crud.model.CatalogueItem",
        "com.toomuch2learn.reactive.nativebuild.catalogue.crud.exception.ErrorResponse"
    },
    access= AccessBits.FULL_REFLECTION)
})*/
public class ApplicationNativeImageHint implements NativeImageConfiguration {
}
