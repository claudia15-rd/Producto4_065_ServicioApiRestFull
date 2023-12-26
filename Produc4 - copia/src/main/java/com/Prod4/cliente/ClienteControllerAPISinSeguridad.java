package com.Prod4.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteControllerAPISinSeguridad {

    @Autowired
    private ClienteServicio servicio;

    @GetMapping("/api/listarClientes")
    public List<Cliente> mostrarClientesListado() {
        return  servicio.listAllClientes();
    }

    @GetMapping("/api/grabarClientes")
    public void grabarClientes(Cliente cliente){
        servicio.save(cliente);
    }

    @GetMapping("/api/borrarCliente")
    public void borrarCliente(Integer Id) throws ClienteNotFoundException {
        servicio.borrarCliente(Id);
    }

}
