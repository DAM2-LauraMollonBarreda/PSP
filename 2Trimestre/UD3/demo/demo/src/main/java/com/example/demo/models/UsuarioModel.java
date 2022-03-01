package com.example.demo.models;

import javax.persistence.*;


/**
 * UsuarioModel
 */
//Para decir que es un modelo real
@Entity
//Para cambiar el nombre de la tabla que no se llame como la clase
@Table(name="usuario")
public class UsuarioModel {

    //Indica que es un id
    @Id
    //Que se genera automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Que es unico y no puede ser nulo
    @Column(unique = true,nullable = false)

    private Long id;
    private String nombre;
    private String email;
    private Integer prioridad;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }



    
}