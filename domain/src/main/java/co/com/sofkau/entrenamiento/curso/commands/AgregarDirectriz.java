package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.*;

public class AgregarDirectriz extends Command {
    private final MentoriaId mentoriaId;

    private final CursoId cursoId;


    private final Directiz directiz;


    public AgregarDirectriz(MentoriaId mentoriaId, CursoId cursoId, Directiz directiz) {
        this.mentoriaId = mentoriaId;
        this.cursoId = cursoId;

        this.directiz = directiz;

    }


    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directiz getDirectiz() {
        return directiz;
    }

    public CursoId getCursoId() {
        return cursoId;
    }





}
