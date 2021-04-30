package org.sid.curso_spring.dto;

import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.model.User;

public class NotaDto {
    private long id;
    private String titulo;
    private String contenido;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Nota getNota() {
        Nota template = new Nota();
        template.setContenido(this.contenido);
        template.setTitulo(this.titulo);
        return template;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
