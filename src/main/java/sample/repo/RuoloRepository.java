package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Ruolo;

@Repository
public interface RuoloRepository extends CrudRepository<Ruolo, Long> 
{
    public Iterable<Ruolo> findRuoloByDescrizione(@Param("descrizione") String descrizione);
    public Iterable<Ruolo> findRuoloById(@Param("id") long id);    
    public Iterable<Ruolo> findByDescrizioneContainingIgnoreCase(String descrizione);
}