package br.infnet.tp1_guilda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanheiroNotFoundException extends RuntimeException {

    public CompanheiroNotFoundException(Long aventureiroId) {
        super("Companheiro vinculado ao aventureiro com ID " + aventureiroId + " não foi encontrado na guilda!");
    }
}
