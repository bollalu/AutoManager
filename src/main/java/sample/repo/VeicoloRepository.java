package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Veicolo;

@Repository
public interface VeicoloRepository extends CrudRepository<Veicolo, Long> 
{
    public Iterable<Veicolo> findVeicoliByTarga(@Param("targa") String targa);
    public Iterable<Veicolo> findByModelloContainingIgnoreCase(String modello);
}