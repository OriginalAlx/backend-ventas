package com.alx.practicasspring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    private String cliente;
    @Positive(message = "El monto de venta debe ser mayor a cero")
    private double monto;

    //Constructor vacío(JPA lo necesita)
    public Venta() {

    }

    public Venta(String cliente, double monto) {
        this.cliente = cliente;
        this.monto = monto;
    }
   
    // Getters y Setters (Necesarios para que Spring lea y escriba los datos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
