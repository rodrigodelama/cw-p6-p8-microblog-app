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
}
