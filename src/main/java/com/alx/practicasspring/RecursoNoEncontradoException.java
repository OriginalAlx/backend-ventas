package com.alx.practicasspring;



public class RecursoNoEncontradoException extends RuntimeException{
    private Long id;
    
    public RecursoNoEncontradoException(Long id){
        // Llamando al constructor de la clase padre con un mensaje personalizado
        super("El recurso con ID " + id + " no fue encontrado en la base de datos");
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }
    
}
