package com.moraraqui.api.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResposta(
        int status,
        String mensagem,
        LocalDateTime timestamp,
        List<String> detalhes
) {}
