package com.example.crudlibro2.controladores;

import com.example.crudlibro2.modelos.Libro;

import java.util.List;

public interface ILibroBD {

    Libro elemento(int id); //Devuelve el elemento dado su id
    Libro elemento(String title); //Devuelve el elemento dado su titulo exacto

    List<Libro> lista(); //Devuelve una lista con todos los elementos registrados

    void agregar(Libro book); //Añade el elemento indicado
    void actualizar(int id, Libro book); //Actualiza datos del elemento dado su id

    void borrar(int id); //Eliminar el elemento indicado con el id

}
