package org.sid.curso_spring.dto;
public class NotaCreateDto {
    private String titulo;
    private String contenido;
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }
    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
