package br.infnet.tp1_guilda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissaoNotFoundException extends RuntimeException {

    public MissaoNotFoundException(Long id) {
        super("Missão com ID " + id + " não foi encontrada na guilda!");
    }
}
