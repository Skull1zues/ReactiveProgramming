package com.soumya.sec02.FileSystem;

import reactor.core.publisher.Mono;

public interface FileService {
    Mono<String> read(String fileName);
    Mono<String> write(String fileName, String content);
    Mono<Void> delete(String fileName);
}
