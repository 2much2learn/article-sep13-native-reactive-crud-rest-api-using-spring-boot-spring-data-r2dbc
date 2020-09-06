package com.toomuch2learn.reactive.nativebuild.catalogue.crud.exception;

import lombok.Data;
import lombok.NonNull;

@Data
public class Error {

    @NonNull private int code;
    @NonNull private String message;
    @NonNull private String description;
}
