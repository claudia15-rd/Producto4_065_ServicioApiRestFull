package com.Prod4.alquiler;
import com.Prod4.cliente.Cliente;
import com.Prod4.cliente.ClienteNotFoundException;
import com.Prod4.cliente.ClienteRepositorio;
import com.Prod4.cliente.ClienteServicio;
import com.Prod4.vehiculo.Vehiculo;
import com.Prod4.vehiculo.VehiculoNotFoundException;
import com.Prod4.vehiculo.VehiculoRepositorio;
import com.Prod4.vehiculo.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AlquilerControllerAPISinSeguridad {

    @Autowired
    private AlquilerServicio servicio;

    @Autowired
    private ClienteServicio servicioCliente;

    @Autowired
    private VehiculoServicio servicioVehiculo;

    @GetMapping("/api/listarAlquilerVehiculos")
    public List<Alquiler> listarAlquilerVehiculos() {
        return  servicio.listAllAlquileres();
    }

    @GetMapping("/api/grabarAlguileres")
    public Alquiler  grabarAlguileres(String lugarRecogida, String lugarDevolucion , String fechaRecogida, String fechaDevolucion, Integer idVehiculo, Integer idCliente) throws ParseException, ClienteNotFoundException, VehiculoNotFoundException {
        Alquiler alquiler= new Alquiler();
        alquiler.setLugarRecogida(lugarRecogida);
        alquiler.setLugarDevolucion(lugarDevolucion);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = fechaRecogida;
        Date fecha=formatter.parse(dateInString);
        String dateInString2=fechaDevolucion;
        Date fecha2=formatter.parse(dateInString2);
        alquiler.setFechaRecogida(fecha);
        alquiler.setFechaDevolucion(fecha2);
        alquiler.setCliente(servicioCliente.get(idCliente));
        alquiler.setVehiculo(servicioVehiculo.get(idVehiculo));
        servicio.save(alquiler);
        return alquiler;
    }

    @GetMapping("/api/borrarAlquiler")
    public void borrarAlquiler(Integer idAlquiler) throws AlquilerNotFoundException {
        servicio.borrarAlquiler(idAlquiler);
    }


}
