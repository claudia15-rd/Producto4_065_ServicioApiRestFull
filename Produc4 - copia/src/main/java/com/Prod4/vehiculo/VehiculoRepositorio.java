package com.Prod4.vehiculo;

import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepositorio extends CrudRepository <Vehiculo, Integer> {
//public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer> {
    public long countVehiculosByIdVehiculos(Integer id);


}
