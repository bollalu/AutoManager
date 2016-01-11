package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.CambioGomme;
import sample.model.Rifornimento;

@Repository
public interface CambioGommeRepository extends CrudRepository<CambioGomme, Long> {
	public Iterable<CambioGomme> findCambioGommeById(@Param("id") long id);
	public Iterable<CambioGomme> findCambioGommeByVeicoloId(long veicolo);
}
