package com.bancoalimentar.Controller;

import com.bancoalimentar.Service.DoacaoService;
import com.bancoalimentar.model.Doacao;
import com.bancoalimentar.model.Entidade;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/doacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoacaoController {

    @Inject
    private DoacaoService service;

    @POST
    public Response criar(Doacao doacao) {
        return Response.ok(service.criar(doacao)).build();
    }

    @GET
    public Response listarAll() {
        return Response.ok(service.listar()).build();
    }

    // GET por ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Doacao doacao = service.buscarPorId(id);
        if (doacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(doacao).build();
    }
     
    // GETdoaçoes por doador
    @GET
    @Path("/doador/{doadorId}")
    public Response listarPorDoador(@PathParam("doadorId") Long doadorId) {
        return Response.ok(service.listarPorDoador(doadorId)).build();
    }

    //get total
    @GET
    @Path("/{id}/total")
    public Response totalAlimentos(@PathParam("id") Long id) {
        Doacao doacao = service.buscarPorId(id);
        if (doacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(doacao.getTotalAlimentos()).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }
        // PUT
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Doacao doacao) {
        return Response.ok(service.atualizar(id, doacao)).build();
    }
}