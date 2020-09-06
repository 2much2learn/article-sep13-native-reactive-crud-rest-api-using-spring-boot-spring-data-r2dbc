package com.toomuch2learn.reactive.nativebuild.catalogue.crud.exception;

public class WrappedException extends RuntimeException{

    public WrappedException(Throwable e) {
        super(e);
    }
}
