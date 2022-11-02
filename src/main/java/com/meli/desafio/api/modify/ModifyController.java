package com.meli.desafio.api.modify;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.desafio.domain.usecase.modify.ModifyUseCase;

@RestController
public class ModifyController {

    private final ModifyUseCase modifyUseCase;

    public ModifyController (ModifyUseCase modifyUseCase) {
        this.modifyUseCase = modifyUseCase;
    }

    @PutMapping("/shortener/enable/{shortUrl}/{user}")
    public ResponseEntity<ModifyResponse> enableShortUrl(@PathVariable String shortUrl, @PathVariable String user) {
        ModifyResponse response = new ModifyResponse();
        response.setResult(this.modifyUseCase.setStatus(shortUrl, user, true));
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/shortener/disable/{shortUrl}/{user}")
    public ResponseEntity<ModifyResponse> disableShortUrl(@PathVariable String shortUrl, @PathVariable String user) {
        ModifyResponse response = new ModifyResponse();
        response.setResult(this.modifyUseCase.setStatus(shortUrl, user, false));
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/shortener/setUrl/{shortUrl}/{user}/{newUrl}")
    public ResponseEntity<ModifyResponse> createShortUrl(@PathVariable String shortUrl, @PathVariable String user, @PathVariable String newUrl) {
        ModifyResponse response = new ModifyResponse();
        response.setResult(this.modifyUseCase.setNewUrl(shortUrl, user, newUrl));
        return ResponseEntity.ok(response);
    }
}
