package com.bancoalimentar.Controller;

import com.bancoalimentar.Service.AlimentoService;
import com.bancoalimentar.model.Alimento;
import com.bancoalimentar.model.Entidade;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alimentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoController {

    @Inject
    private AlimentoService service;

    // POST
    @POST
    public Response criar(Alimento alimento) {
        return Response.ok(service.criar(alimento)).build();
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
        Alimento alimento = service.buscarPorId(id);
        if (alimento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(alimento).build();
    }

    // GET vencidos (validade < data atual da consulta)
    @GET
    @Path("/vencidos")
    public Response listarVencidos() {
        return Response.ok(service.listarVencidos()).build();
    }


    @GET
    @Path("/validos")
    public Response listarValidos() {
        return Response.ok(service.listarValidos()).build();
    }

    // GET atraves do id da doação
    @GET
    @Path("/origem/{origemId}")
    public Response listarPorOrigem(@PathParam("origemId") Long origemId) {
        return Response.ok(service.listarPorOrigem(origemId)).build();
    }

    // PUT
  @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Alimento alimento) {
        Alimento atualizado = service.atualizar(id, alimento);
        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(atualizado).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }

  
}