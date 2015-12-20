package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> 
{
    public Iterable<Marca> findMarcaByDescrizione(@Param("descrizione") String descrizione);
    public Iterable<Marca> findByDescrizioneContainingIgnoreCase(String descrizione);
}