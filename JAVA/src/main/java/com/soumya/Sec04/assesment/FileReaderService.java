package com.soumya.Sec04.assesment;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {
    Flux<String > read(Path path);
}
