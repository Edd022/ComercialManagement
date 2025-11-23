package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.AlmacenProductoEntity;
import com.acmspring.co.gestioncomercial.repository.AlmacenProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlmacenProductoService {

    @Autowired
    AlmacenProductoRepository repoAlmacenProducto;

    public List<AlmacenProductoEntity> getAll(){return repoAlmacenProducto.findAll();}

}
