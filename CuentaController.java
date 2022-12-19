package com.serviciobancario.Controller;
import com.serviciobancario.Models.Cuenta;
import com.serviciobancario.Dao.CuentaDao;
import com.serviciobancario.Service.CuentaService;


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
@RequestMapping (" / cuenta")
public class CuentaController {
    /**
     *
     */
    private static final String LIST_ID = "/list/{id}";
    @Autowired
    private CuentaDao cuentaDao;
    @Autowired
    private CuentaService cuentaService;

    /**
     * @param cuenta
     * @return
     */
    @PostMapping(value = "/")
    public ResponseEntity<Cuenta> agregar(@RequestBody Cuenta cuenta) {
        Cuenta obj = cuentaService.save(cuenta);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
    /**
     * @param id
     * @return
     */
    @DeleteMapping(value = "/(id)")
    public ResponseEntity<Cuenta> eliminar (@PathVariable String id){
        Cuenta obj = cuentaService.findById(id);

        if (obj != null) 
            cuentaService.save(obj);
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
/**
 * @param cuenta
 * @return
 */
@PutMapping(value = "/")
public ResponseEntity<Cuenta> editar(@RequestBody Cuenta cuenta) {
    Cuenta obj =
    cuentaService.findById(cuenta.getId_cuenta());
    if (obj != null) {
        obj.setId_cuenta(cuenta.getId_cuenta());
        final Cuenta Cuenta;
        obj.setId_cuenta(cuenta.getId_cuenta());
        cuentaService.save(obj);
    }
    else {
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK);
}
        @GetMapping ("/list")
public List<Cuenta> consultarTodo() {

}

}