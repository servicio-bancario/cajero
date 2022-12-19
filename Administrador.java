package com.serviciobancario.Controller;
import com.serviciobancario.Models.Administrador;
import com.serviciobancario.Dao.AdministradorDao;
import com.serviciobancario.Service.AdministradorService;

import ch.qos.logback.core.net.server.Administrador;

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
@RequestMapping (" / cliente")
public class AdministradorController {
    /**
     *
     */
    private static final String LIST_ID = "/list/{id}";
    @Autowired
    private AdministradorDao administradorDao;
    @Autowired
    private AdministradorService administradorService;

    /**
     * @param administrador
     * @return
     */
    @PostMapping(value = "/")
    public ResponseEntity<Administrador> agregar(@RequestBody Administrador administrador) {
        Administrador obj = administradorService.save(administrador);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
    /**
     * @param id
     * @return
     */
    @DeleteMapping(value = "/(id)")
    public ResponseEntity<administrador> eliminar (@PathVariable String id){
        Administrador obj = administradorService.findById(id);

        if (obj != null) 
            administradorService.save(obj);
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
/**
 * @param administrador
 * @return
 */
@PutMapping(value = "/")
public ResponseEntity<Administrador> editar(@RequestBody Administrador administrador) {
    Administrador obj =
    administradorService.findById(cliente.getId_cliente());
    if (obj != null) {
        obj.setNombre_administrador(administrador.getNombre_administrador());
        Administrador administrador;
        obj.setClave_administrador(administrador.getClave_administrador());
        administradorService.save(obj);
    }
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
        @GetMapping ("/list")
public List<Administrador> consultarTodo() {

}
/**
 * @param id
 * @return
 */
@GetMapping(LIST_ID)
public Administrador consultaPorId(@PathVariable String id){
    return administradorService.findById(id);
}


