package com.Prod4.vehiculo;

import com.Prod4.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehiculoControllerAPIConSeguridad {

    @Autowired
    private VehiculoServicio servicio;

    @GetMapping("/apiSecure/listarVehiculos")
    public List<Vehiculo> mostrarVehiculos()
    {
        return servicio.listAllVehiculos();
    }

    @GetMapping("/apiSecure/guardarVehiculo")
    public void guardarVehiculo(Vehiculo vehiculo) {servicio.save(vehiculo);}

    @GetMapping("/apiSecure/borrarVehiculo")
    public void borrarVehiculo(Integer id) throws VehiculoNotFoundException {servicio.borrarVehiculo(id);}



}
