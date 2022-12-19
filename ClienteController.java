package com.serviciobancario.Controller;
import com.serviciobancario.Models.Cliente;
import com.serviciobancario.Dao.ClienteDao;
import com.serviciobancario.Service.ClienteService;

import ch.qos.logback.core.net.server.Client;

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
public class ClienteController {
    /**
     *
     */
    private static final String LIST_ID = "/list/{id}";
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private ClienteService clienteService;

    /**
     * @param cliente
     * @return
     */
    @PostMapping(value = "/")
    public ResponseEntity<Cliente> agregar(@RequestBody Cliente cliente) {
        Cliente obj = clienteService.save(cliente);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
    /**
     * @param id
     * @return
     */
    @DeleteMapping(value = "/(id)")
    public ResponseEntity<Client> eliminar (@PathVariable String id){
        Cliente obj = clienteService.findById(id);

        if (obj != null) 
            clienteService.save(obj);
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
/**
 * @param cliente
 * @return
 */
@PutMapping(value = "/")
public ResponseEntity<Cliente> editar(@RequestBody Cliente cliente) {
    Cliente obj =
    clienteService.findById(cliente.getId_cliente());
    if (obj != null) {
        obj.setNombre_cliente(cliente.getNombre_cliente());
        Cliente clente;
        obj.setClave_cliente(clente.getClave_cliente());
        clienteService.save(obj);
    }
    else
        return new ResponseEntity<> (obj, HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<> (obj, HttpStatus.OK) ;
}
        @GetMapping ("/list")
public List<Cliente> consultarTodo() {

}
/**
 * @param id
 * @return
 */
@GetMapping(LIST_ID)
public Cliente consultaPorId(@PathVariable String id){
    return clienteService.findById(id);
}


