package com.alx.practicasspring;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VentaRepository extends JpaRepository<Venta, Long>{
    List<Venta> findByCliente(String cliente);
    
    // Traer ventas donde el monto sea mayor a un valor dado
    List<Venta> findByMontoGreaterThan(double monto);
    
    // Traer ventas donde el nombre del cliente contenga un texto (búsqueda parcial)
    List<Venta> findByClienteContaining(String texto);
    
    List<Venta> findByMontoBetween(double min, double max);
}
