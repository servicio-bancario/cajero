package com.serviciobancario.Controller;
import com.serviciobancario.Models.Transaccion;
import com.serviciobancario.Dao.TransaccionDao;
import com.serviciobancario.Service.TransaccionService;

import ch.qos.logback.core.net.server.Transaccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ RestController
@CrossOrigin ("*")
@RequestMapping (" / transaccion")
public class TrasaccionController {
    /**
     *
     */
    private static final String LIST_ID = "/list/{id}";
    @Autowired
    private TransaccioneDao transaccionDao;
    @Autowired
    private TransaccionService transaccionService;

    /**
     * @param transaccion
     * @return
     */
    @PostMapping(value = "/")
    public ResponseEntity<Cliente> agregar(@RequestBody Transaccion transaccion) {
        Transaccion obj = transaccionService.save(transaccion);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
    /**
     * @param id
     * @return
     */
    @DeleteMapping(value = "/(id)")
    public ResponseEntity<Transaccion> eliminar (@PathVariable String id){
        Cliente obj = transaccionService.findById(id);

        if (obj != null) 
            transaccionService.save(obj);
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
/**
 * @param transaccion
 * @return
 */
@PutMapping(value = "/")
public ResponseEntity<Transaccion> editar(@RequestBody Transaccion transaccion) {
    Transaccion obj =
    transaccionService.findById(transaccion.getId_transaccion());
    if (obj != null) {
        obj.setNombre_transaccion(transaccion.getNombre_transaccion());
        Transaccion transaccion;
        obj.setClave_transaccion(transaccion.getClave_transaccion());
        transaccionService.save(obj);
    }
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
        @GetMapping ("/list")
public List<Transaccion> consultarTodo() {

}
/**
 * @param id
 * @return
 */
@GetMapping(LIST_ID)
public Cliente consultaPorId(@PathVariable String id){
    return transaccionService.findById(id);
}


