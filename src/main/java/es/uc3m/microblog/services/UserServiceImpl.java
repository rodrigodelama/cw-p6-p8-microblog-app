package es.uc3m.microblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.uc3m.microblog.model.User;
import es.uc3m.microblog.model.UserRepository;

/*
 * La anotación @Service indica al entorno que esta clase implementa un servicio.
 * Una instancia de este servicio estará disponible para otras partes de la
 * aplicación a través del sistema de inyección de dependencias de Spring.
 */
@Service
public class UserServiceImpl implements UserService {

    /*
     * De hecho, obtener objetos del sistema de inyección de dependencias es tan
     * sencillo como añadir la anotación @Autowired. En el código anterior, el método
     * register tiene acceso a las instancias del repositorio de usuarios y del
     * codificador de contraseñas gracias a este mecanismo.
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Además, puedes ver que el método register simplemente reemplaza la contraseña del
     * usuario por su versión cifrada (usando el bean del codificador de contraseñas) y,
     * finalmente, almacena el objeto en la base de datos con el método save de
     * UserRepository, que heredan todas las interfaces de repositorio de CrudRepository.
     */
    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
