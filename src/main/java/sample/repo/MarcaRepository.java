package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> 
{
    public Iterable<Marca> findMarcaByMarca(@Param("marca") String marca);
    public Iterable<Marca> findByMarcaContainingIgnoreCase(String marca);
}