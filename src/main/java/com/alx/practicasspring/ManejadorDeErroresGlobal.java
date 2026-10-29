package com.alx.practicasspring;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//  Esta anotación le dice a Spring: "Vigila todos los controladores de la app"
@RestControllerAdvice
public class ManejadorDeErroresGlobal {

    // Esta anotación le dice: "Si ves ESTA excepción específica, ejecuta este método"
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        // Creación de un mapa que se convertirá en un JSON de respuesta
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value()); // Error 404
        errorResponse.put("error", "Recurso no encontrado");
        errorResponse.put("mensaje", ex.getMessage());
        errorResponse.put("id", ex.getId());

        // Devolvemos el JSON y le decimos al navegador que el estado HTTP es 404 (No encontrado)
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Este método captura los errores de validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value()); //Error 404
        errorResponse.put("error", "Error de validación");

        // Aquí extraemos todos los mensajes de error de los campos que fallaron
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        errorResponse.put("detalles", errores);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
