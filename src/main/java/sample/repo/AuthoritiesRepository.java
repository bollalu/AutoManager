package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> 
{
    public Iterable<Authorities> findAuthoritiesByUsername(@Param("username") String username);
    public Iterable<Authorities> findAuthoritiesByAuthority(@Param("authority") String authority);
    public Iterable<Authorities> findByUsernameContainingIgnoreCase(String username);
	public void deleteByUsername(String username);
}