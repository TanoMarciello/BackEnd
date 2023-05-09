/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.afunes.Controller;

import com.portfolio.afunes.Entity.Proyecto;
import com.portfolio.afunes.Dto.dtoProyecto;
import com.portfolio.afunes.Security.Controller.Mensaje;
import com.portfolio.afunes.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

/**
 *
 * @author agufu
 */
@RestController
@RequestMapping("portfolio")
@CrossOrigin(origins = {"http://localhost:4200","https://portfolio-12fb1.web.app"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoport){
        if(StringUtils.isBlank(dtoport.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreP(dtoport.getNombreP()))
            return new ResponseEntity(new Mensaje("EsE proyecto existe"), HttpStatus.BAD_REQUEST);
    
        Proyecto portfolio = new Proyecto(dtoport.getNombreP(), dtoport.getDescripcionP(), dtoport.getImgP(), dtoport.getWebP() );
        sProyecto.save(portfolio);
        
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoport){
        //Valido si existe el id
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
            //Existe port con mismo nombre?
        if(sProyecto.existsByNombreP(dtoport.getNombreP()) && sProyecto.getByNombreP(dtoport.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese Proyecto ya existe"), HttpStatus.BAD_REQUEST);
            //No puede ser vacio
        if(StringUtils.isBlank(dtoport.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Proyecto portfolio = sProyecto.getOne(id).get();
        portfolio.setNombreP(dtoport.getNombreP());
        portfolio.setDescripcionP(dtoport.getDescripcionP());
        portfolio.setImgP(dtoport.getImgP());
        portfolio.setWebP(dtoport.getWebP() );
        
        sProyecto.save(portfolio);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Proyecto educacion = sProyecto.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         //Valido si existe el id
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
}
