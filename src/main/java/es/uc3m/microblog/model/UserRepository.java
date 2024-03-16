package es.uc3m.microblog.model;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.microblog.model.User;

/* 
 * Por último, será necesario disponer del código que permita crear, leer,
 * actualizar y borrar usuarios (CRUD, del inglés create, read, update, delete)
 * en la base de datos.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
    
    User findByEmail(String email);
}