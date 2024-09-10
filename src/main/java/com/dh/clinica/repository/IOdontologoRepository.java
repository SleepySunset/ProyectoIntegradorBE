package com.dh.clinica.repository;

import com.dh.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository <Odontologo,Integer> {


    //@Query("Select o from Odontologo o where o.nroMatricula LIKE %:nroMatricula%")
    List<Odontologo> findByNroMatriculaLike(String nroMatricula);

}
