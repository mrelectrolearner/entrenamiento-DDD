package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.Mentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectriz;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AgregarDirectrizUseCaseTest {


    @InjectMocks
    private AgregarDirectrizUseCase useCaseDirectiz;
    @InjectMocks
    private AgregarMentoriaUseCase useCaseAgregarMentoria;

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarUnaDirectizHappyPass(){
        //arrange
        CursoId cursoId = CursoId.of("ddd");
        MentoriaId mentoriaId=  MentoriaId.of("use");
        Directiz directiz=new Directiz("Agregando directriaz");
        var commandDirectriz = new AgregarDirectriz( mentoriaId, cursoId, directiz);



        when(repository.getEventsBy("ddd")).thenReturn(history());
        useCaseDirectiz.addRepository(repository);

        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(commandDirectriz.getCursoId().value())
                .syncExecutor(useCaseDirectiz, new RequestCommand<>(commandDirectriz))
                .orElseThrow()
                .getDomainEvents();


        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);


        Assertions.assertEquals("Agregando directriaz", event.getDirectiz().value());

    }



    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Curso complementario para el training");
        Nombre nombreMentoria = new Nombre("martes");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        MentoriaId mentoriaId=  MentoriaId.of("use");
        Mentoria mentoria =new Mentoria(mentoriaId,  nombre, fecha);
        var event = new CursoCreado(
                nombre,
                descripcion

        );
        event.setAggregateRootId("xxx");
        var event2 = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha

        );
        event.setAggregateRootId("xxxxx");



        return List.of(event, event2);
    }


}