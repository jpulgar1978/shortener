package com.meli.desafio.api.create;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.desafio.domain.usecase.create.CreateUseCase;

@RestController
public class CreateController {

    private final CreateUseCase createUseCase;

    public CreateController (CreateUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }

    @PostMapping("/shortener/create")
    public ResponseEntity<CreateResponse> createShortUrl(@RequestBody CreateRequest request) {
        CreateResponse response = new CreateResponse();
        String result = this.createUseCase.create(request.getUrl());
        response.setShortUrl(result);
        response.setMessage(result.equals("")?"URL NOT SHORTENED":"URL SHORTENED");
        return ResponseEntity.ok(response);
    }
}
