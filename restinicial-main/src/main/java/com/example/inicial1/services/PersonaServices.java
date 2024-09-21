package com.example.inicial1.services;

import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inicial1.entities.Persona;
import java.util.List;
import java.util.Optional;

@Service

public class PersonaServices implements BaseService<Persona> {
    @Autowired
    private PersonaRepository personaRepository;


    @Override
    @Transactional  //hace transacciones ahorro begin commit y rollback
    public List<Persona> findAll() throws Exception {
        try {
            List<Persona> entities = personaRepository.findAll();

            //obtengo de la bd todas las personas registradas
        } catch (Exception e) {
            throw new Exception(e.getMessage());
            //nueva excepcion capturada por el controlador

        }
        return List.of();
    }

    @Override
    @Transactional
    public Persona findById(long id) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            return entityOptional.get();
            //encuentro entidad y lanso excepcion
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try{
            entity = personaRepository.save(entity);
            return entity;
            //uso el save del repositorio
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(long id, Persona entity) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            //obtengo la persona que quiero actualizar
            Persona persona = entityOptional.get();
            //creo una nueva persona
            persona= personaRepository.save(persona);
            return persona;
            //actalizo nuevamente y retrno la persona actualizada
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(long id) throws Exception {
        try{
            if(personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
            //condicional para saber si existe alguna persona con ese id , si existe lo borro.sino lanso excepcion
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
