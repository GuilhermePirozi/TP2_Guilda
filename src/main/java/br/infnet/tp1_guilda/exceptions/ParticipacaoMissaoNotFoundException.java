package br.infnet.tp1_guilda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipacaoMissaoNotFoundException extends RuntimeException {

    public ParticipacaoMissaoNotFoundException(Long missaoId, Long aventureiroId) {
        super("Participação na missão " + missaoId + " para o aventureiro " + aventureiroId + " não foi encontrada!");
    }
}
