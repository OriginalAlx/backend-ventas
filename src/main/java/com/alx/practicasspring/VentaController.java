package com.alx.practicasspring;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    private final VentaRepository ventaRepo;
    
    //Spring inyecta el repositorio automáticamente aquí
    public VentaController(VentaRepository ventaRepo){
        this.ventaRepo = ventaRepo;
    }
    
    // Crear
    @PostMapping
    public Venta crear(@Valid @RequestBody Venta venta){
        return ventaRepo.save(venta);
    }
    
    // Leer
    @GetMapping
    public List<Venta> leerTodos(){
        return ventaRepo.findAll();
    }
    
    // Actualizar
    @PutMapping
    public Venta actualizar(@Valid @RequestBody Venta venta){
        return ventaRepo.save(venta);
    }
    
    // Borrar
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id){
        // Verificamos si existe. Si no existe lanzamos una expeción personalizada
        if(!ventaRepo.existsById(id)){
            throw new RecursoNoEncontradoException(id);
        }
        // Si existe, lo borramos
        ventaRepo.deleteById(id);
    }
    
    // Endpoint para buscar ventas caras
    @GetMapping("/montos-altos")
    public List<Venta> buscarMontosAltos(@RequestParam double minimo){
        return ventaRepo.findByMontoGreaterThan(minimo);
    }
    
    // Endpoint para buscar clientes por nombre(ej: "Ju" para encontrar a "Juan)
    @GetMapping("/buscar-cliente")
    public List<Venta> buscarCliente(@RequestParam String texto){
        return ventaRepo.findByClienteContaining(texto);
    }
    
    @GetMapping("/monto-entre")
    public List<Venta> findByMontoBetween(@RequestParam double min, double max){
        return ventaRepo.findByMontoBetween(min, max);
    }
    
}
