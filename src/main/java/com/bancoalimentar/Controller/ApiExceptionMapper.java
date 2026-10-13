package com.bancoalimentar.Controller;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

// Apanha TODAS as RuntimeException da API e devolve 400 com JSON
@Provider
public class ApiExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("erro", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}