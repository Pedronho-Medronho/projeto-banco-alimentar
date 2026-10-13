package com.bancoalimentar.Controller;

import com.bancoalimentar.Service.ArmazemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/armazem")
@Produces(MediaType.APPLICATION_JSON)
public class ArmazemController {

    @Inject
    private ArmazemService service;

    // GET estado do armazém
    @GET
    public Response estado() {
        return Response.ok(service.estado()).build();
    }
}