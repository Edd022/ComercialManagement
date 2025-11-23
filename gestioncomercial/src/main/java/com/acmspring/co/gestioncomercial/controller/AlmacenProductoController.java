package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.AlmacenProductoEntity;
import com.acmspring.co.gestioncomercial.services.AlmacenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("apigc/v1/almacenproducto")
public class AlmacenProductoController {
    @Autowired
    private AlmacenProductoService Service;

    @GetMapping
    public List<AlmacenProductoEntity> getAll(){return Service.getAll();}
}
