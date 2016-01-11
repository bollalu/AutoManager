package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import sample.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, String> 
{
    public Iterable<Users> findUsersByUsername(@Param("username") String username);  
    public Iterable<Users> findByUsernameContainingIgnoreCase(String username);
	public void deleteByUsername(String username);    
    }