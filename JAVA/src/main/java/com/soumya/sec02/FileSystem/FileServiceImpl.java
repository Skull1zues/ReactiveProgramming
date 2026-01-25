package com.soumya.sec02.FileSystem;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService{

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    public static final Path PATH = Path.of("src/main/resources/Filesystem");

    @Override
    public Mono<String> read(String fileName) {

        return Mono.fromSupplier(()-> {
            try {
                return Files.readString(PATH.resolve(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<String> write(String fileName, String content) {
        //Util.sleepSecond(1);
        return Mono.fromRunnable(() ->this.writeFile(fileName,content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> this.deleteFile(fileName));
    }

    private void writeFile(String fileName, String content){
        try {
            Files.writeString(PATH.resolve(fileName),content);
            logger.info("Created {}",fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteFile(String fileName){
        try {
            Files.delete(PATH.resolve(fileName));
            logger.info("Deleted {}",fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
