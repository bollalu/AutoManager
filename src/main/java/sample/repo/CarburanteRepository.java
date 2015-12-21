package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Carburante;

@Repository
public interface CarburanteRepository extends CrudRepository<Carburante, Long> 
{
    public Iterable<Carburante> findCarburanteByDescrizione(@Param("descrizione") String descrizione);
    public Iterable<Carburante> findByDescrizioneContainingIgnoreCase(String descrizione);
}