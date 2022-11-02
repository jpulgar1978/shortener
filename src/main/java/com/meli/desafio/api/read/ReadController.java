package com.meli.desafio.api.read;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.meli.desafio.domain.usecase.read.ReadUseCase;

@RestController
public class ReadController {

    private final ReadUseCase readUseCase;

    public ReadController (ReadUseCase readUseCase) {
        this.readUseCase = readUseCase;
    }

    @GetMapping("/shortener/read/{shortUrl}")
    public ResponseEntity<ReadResponse> createShortUrl(@PathVariable String shortUrl) {
        ReadResponse response = new ReadResponse();
        response.setUrl(this.readUseCase.read(shortUrl));
        return ResponseEntity.ok(response);
    }
}
