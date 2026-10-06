package com.bancoalimentar.Controller;

import com.bancoalimentar.Service.EntidadeService;
import com.bancoalimentar.model.Entidade;
import com.bancoalimentar.model.Entidade.TipoEntidade;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/entidades")
@Produces(MediaType.APPLICATION_JSON)
public class EntidadeController {

    @Inject
    private EntidadeService service;

    // POST (JSON)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(Entidade entidade) {
        return Response.ok(service.criar(entidade)).build();
    }

    // POST (formulário)
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response criarForm(
            @FormParam("nome") String nome,
            @FormParam("morada") String morada,
            @FormParam("contacto") String contacto,
            @FormParam("tipo") String tipo) {
        Entidade entidade = new Entidade();
        entidade.setNome(nome);
        entidade.setMorada(morada);
        entidade.setContacto(contacto);
        entidade.setTipo(tipo.equals("DOADOR") ? TipoEntidade.DOADOR : TipoEntidade.BENEFICIARIO);
        return Response.ok(service.criar(entidade)).build();
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
        Entidade entidade = service.buscarPorId(id);
        if (entidade == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entidade).build();
    }

    // PUT
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Entidade entidade) {
        Entidade atualizada = service.atualizar(id, entidade);
        if (atualizada == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(atualizada).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }
}