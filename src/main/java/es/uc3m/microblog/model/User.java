package es.uc3m.microblog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/*
 * @Entity: indica que la clase User es una entidad cuyas
 * instancias serán almacenadas en base de datos.
 */
@Entity
public class User {
    
    /*
     * @Id: la propiedad identifica unívocamente al objeto de esta clase,
     * y será utilizada como clave primaria de la tabla en la base de datos.
     * Esto es, dos objetos de la clase User representan la misma entidad
     * si el valor de esta propiedad es el mismo en ambos, y representan
     * entidades distintas en caso contrario. Normalmente, todas las entidades
     * que definas deben contar con una propiedad de tipo entero anotada con @Id.
     */
    @Id
    /*
     * @GeneratedValue: la propiedad, que debe ser un entero que identifique al objeto,
     * tomará un valor que se generará automáticamente por el sistema cada vez que se
     * cree un nuevo objeto de la clase User. El sistema asegurará que dicho valor sea
     * único. Tus aplicaciones no deben inicializar esta propiedad al crear nuevos objetos,
     * de tal forma que pueda hacerlo el sistema.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*
     * @Column: indica al sistema que la propiedad debe ser mapeada a una columna de la
     * tabla con ciertas características adicionales, como que no puede tomar un valor
     * null, que su valor debe ser único en la aplicación (como es el caso de las columna
     * de nombre de usuario y dirección de correo electrónico), o que se aplica una
     * restricción de longitud.
     */
    @Column(nullable = false, length = 64)
    /* 
    * @NotBlank: se trata de una etiqueta de validación de datos. El sistema impedirá que
    * se asigne a esta propiedad un valor blanco entendiendo como tal un valor null o bien
    * una cadena de texto que no contenga al menos un carácter no blanco (distinto de espacio
    * en blanco, fin de línea, tabulador, etc.).
    */
    @NotBlank
    /*
     * @Size: se trata de una etiqueta de validación de datos. El sistema validará que no se
     * asigne a esta propiedad un valor cuya longitud incumpla la restricción que se establezca.
     * En el ejemplo, el nombre del usuario debe tener como máximo 64 caracteres.
     */
    @Size(max = 64)
    private String name;

    @Column(unique = true, nullable = false, length = 64)
    /*
     * @Email: se trata de una etiqueta de validación de datos. El sistema validará que no se
     * asigne a esta propiedad un valor que no sea conforme a la sintaxis de las direcciones
     * de correo electrónico.
     */
    @Email
    @NotBlank
    @Size(max = 64)
    private String email;

    /*
     * @Lob: indica que esta columna debe ser almacenada en la base de datos previendo que
     * contendrá cadenas de caracteres o secuencias binarias potencialmente largas. En este
     * caso se usa porque la descripción del usuario podría ser un fragmento largo de texto.
     */
    @Lob
    private String description;

    @Column(nullable = false)
    @NotBlank
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + name + " <" + email + ">";
    }
}