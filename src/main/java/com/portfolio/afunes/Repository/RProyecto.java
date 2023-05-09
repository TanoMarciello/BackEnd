/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.afunes.Repository;


import com.portfolio.afunes.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agufu
 */
@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer>{
     public Optional<Proyecto> findByNombreP(String nombreP);
     public boolean existsByNombreP(String nombreP);
}
 