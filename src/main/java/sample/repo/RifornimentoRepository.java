package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sample.model.Rifornimento;
import sample.model.Veicolo;

@Repository
public interface RifornimentoRepository extends CrudRepository<Rifornimento, Long> {

	public Iterable<Rifornimento> findRifornimentoById(@Param("id") long id);
	public Iterable<Rifornimento> findRifornimentoByVeicoloId(long veicolo);

}