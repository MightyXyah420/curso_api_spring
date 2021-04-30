package org.sid.curso_spring.service;

import java.util.Optional;

import org.sid.curso_spring.model.Nota;

public interface NotaService {
    //
    Nota save(Nota nota);
    void delete(Nota nota);

    Optional<Nota> readNota(long id);
}
