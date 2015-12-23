package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Modello;

@Repository
public interface ModelloRepository extends CrudRepository<Modello, Long> 
{
    public Iterable<Modello> findModelloByDescrizione(@Param("descrizione") String descrizione);
    public Iterable<Modello> findModelloByMarca(@Param("marca") long marca);
    public Iterable<Modello> findModelloById(@Param("id") long id);
    public Iterable<Modello> findByDescrizioneContainingIgnoreCase(String descrizione);    
}