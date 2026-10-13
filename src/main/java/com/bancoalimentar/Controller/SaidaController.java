package com.bancoalimentar.Controller;

import com.bancoalimentar.Service.SaidaService;
import com.bancoalimentar.model.Saida;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/saidas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaidaController {

    @Inject
    private SaidaService service;

        // POST
    @POST
    public Response criar(Saida saida) {
        try {
            return Response.ok(service.criar(saida)).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(java.util.Map.of("erro", e.getMessage()))
                    .build();
        }
    }

    
    // GET
    @GET
    public Response listarAll() {
        return Response.ok(service.listar()).build();
    }

    // GET por ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Saida saida = service.buscarPorId(id);
        if (saida == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(saida).build();
    }

    // GET por beneficiário
    @GET
    @Path("/beneficiario/{beneficiarioId}")
    public Response listarPorBeneficiario(@PathParam("beneficiarioId") Long beneficiarioId) {
        return Response.ok(service.listarPorBeneficiario(beneficiarioId)).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }
}