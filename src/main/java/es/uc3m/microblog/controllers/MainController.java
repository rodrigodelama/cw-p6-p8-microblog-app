package es.uc3m.microblog.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uc3m.microblog.model.Message;
import es.uc3m.microblog.model.User;

//practica 6
// Nuevas sentencias import:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uc3m.microblog.model.UserRepository;
import es.uc3m.microblog.services.UserService;

// Nuevas clases a importar
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping(path = "/")
    public String mainView(Model model) {
        /*
         * Este controlador crea, a modo de demostración porque la aplicación
         * todavía no está conectada con una base de datos, un usuario y dos
         * publicaciones en la red social (observa el código de las clases User
         * y Publication en el paquete es.uc3m.microblog.model). A continuación,
         * el programa añade una lista con las dos publicaciones creadas como
         * atributo del objeto de la clase Model. Finalmente, devuelve la cadena
         * de texto "main_view".
         */
        List<Message> messages = new ArrayList<Message>();
        
        User user = new User();
        user.setId(1);
        user.setEmail("mary@example.com");
        user.setName("mary");

        Message message = new Message();
        message.setId(1);
        message.setUser(user);
        message.setText("Test post");
        message.setTimestamp(new Date());
        messages.add(message);

        message = new Message();
        message.setId(2);
        message.setUser(user);
        message.setText("Another post");
        message.setTimestamp(new Date());
        messages.add(message);
        
        // Modifica el controlador para que cree e incluya en la lista una nueva publicación.
        message = new Message();
        message.setId(3);
        message.setUser(user);
        message.setText("A new post");
        message.setTimestamp(new Date());
        messages.add(message);
        
        model.addAttribute("messages", messages);
        return "main_view";
        /*
         * El hecho de devolver esta cadena de texto le indica al entorno que
         * debe devolver al cliente la salida proporcionada por la plantilla de
         * código src/main/resources/templates/main_view.html. El objeto Model
         * será accesible a esta plantilla, lo cual le permitirá acceder a los
         * objetos de ambos mensajes y, por tanto, mostrar su contenido. Observa
         * el contenido de esta plantilla:
         */
    }

    @GetMapping(path = "/user")
    public String userView(Model model) {
        
        /* Ejercicio 4
         * Vista del perfil público de un usuario
         * De forma análoga a lo hecho en la vista principal, crea un método controlador y
         * una plantilla para la vista que muestra el perfil público de un usuario.
         * 
         * En primer lugar, debes crear un nuevo método en la clase MainController que cree un
         * usuario de prueba, así como una lista con algunos mensajes de dicho usuario. Debes
         * además asignarle una ruta distinta a la de la vista principal como, por ejemplo, /user.
         * 
         */
        List<Message> messages = new ArrayList<Message>();

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("james@exercise2.com");
        user2.setName("james");

        Message message = new Message();
        message.setId(1);
        message.setUser(user2);
        message.setText("Test post");
        message.setTimestamp(new Date());
        messages.add(message);

        message = new Message();
        message.setId(2);
        message.setUser(user2);
        message.setText("Another post");
        message.setTimestamp(new Date());
        messages.add(message);

        model.addAttribute("user", user2);
        model.addAttribute("messages", messages);
        return "user_view";
    }

    @GetMapping(path = "/message")
    public String messageView(Model model) {
        /*
         * Ejercicio 5
         * Plantilla de la vista de mensaje
         * 
         * De forma similar a la vista principal y a la vista del perfil público de
         * un usuario, crea el controlador y la plantilla para la vista que muestra
         * un mensaje concreto junto con las respuestas al mismo.
         * 
         * Primero, añade un nuevo método a la clase MainController que cree un mensaje
         * de prueba, así como una lista con algunas respuestas (también de prueba) a
         * dicho mensaje. Asígnale una ruta, como /message.
         * 
         * A continuación, debes crear una nueva plantilla Thymeleaf que muestre dichos
         * objetos, con un diseño que sea consistente con las otras dos vistas que ya tienes.
         * 
         */
        
        List<Message> messages = new ArrayList<Message>();

        User user3 = new User();
        user3.setId(3);
        user3.setEmail("max@exercise5.com");
        user3.setName("max");

        Message message = new Message();
        message.setId(2);
        message.setUser(user3);
        message.setText("Another post");
        message.setTimestamp(new Date());
        messages.add(message);

        model.addAttribute("user", user3);
        model.addAttribute("messages", messages);
        return "message_view";
    }

    @GetMapping(path = "/signup")
    public String signUpForm() {
        return "signup";
    }

    //Practica 6
    /*
     * Los datos del formulario son mapeados automáticamente por el entorno al objeto User y a la cadena passwordRepeat
     * que el método recibe como parámetros, gracias a las anotaciones @modelAttribute y RequestParam. Para hacerlo, el
     * entorno empareja los nombres de los parámetros del formulario con los nombres de los atributos de la clase User
     * y con el nombre del parámetro passwordRepeat.
     * 
     * Las instancias necesarias del repositorio de usuarios y del servicio de usuarios se obtienen del sistema de
     * inyección de dependencias a través de la anotación @Autowired.
     * 
     * Si la dirección de correo electrónico ya está en la base de datos o las dos contraseñas no coinciden, el usuario
     * es redirigido de nuevo al formulario de registro. Se añade un parámetro a la URL, que se usará en un ejercicio
     * posterior para proporcionar retroalimentación al usuario.
     * 
     * Finalmente, se almacena el usuario en la base de datos usando el servicio de usuarios.
     */
    // Nuevos atributos:
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Código alternativo para el método "signup" con mensaje POST:
    @PostMapping(path = "/signup")
    public String signUp(@Valid @ModelAttribute("user") User user,
                        BindingResult bindingResult,
                        @RequestParam(name = "passwordRepeat") String passwordRepeat) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "redirect:signup?duplicate_email";
        }
        if (!user.getPassword().equals(passwordRepeat)) {
            return "redirect:signup?passwords";
        }
        userService.register(user);
        return "redirect:login?registered";
    }

    @GetMapping(path = "/login")
    public String loginForm() {
        return "login";
    }
}
