package com.serviciobancario.Service;
import com.serviciobancario.Models.Administrador;
import com.serviciobancario.Dao.AdministradorDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorDao administradorDao;

    @Transactional (readOnly =false )
    public AdministradorService save(Administrador administrador) {
        return administradorDao.save (administrador);
    }
    @Transactional (readOnly = false)
    public void delete (String id) {
        administradorDao.deleteById(id);
    }
    @Transactional (readOnly = true)
    public Administrador findById (String id)
    return administradorDao.findById(id).orElse(null);
}
@Transactional (readOnly = true)
public List<Cliente> findAll(){
    return (List<Administrador>) administradorDao.findAll();
}

}