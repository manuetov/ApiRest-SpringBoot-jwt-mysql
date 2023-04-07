package com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    // en el momento que se guarde un user(parent) se guardará tambien su role(child)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    // user_id => clave foránea que relaciona las dos tablas por sus id(claves primarias) respectivas
    // role_id => relación many to many
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    /* FetchType.EAGER => indica que se deben cargar todas las relaciones de la entidad
    * al mismo tiempo que se carga la propia entidad. Esto significa que todas las
    * propiedades de la entidad y sus relaciones se recuperarán de la base de datos
    * al momento de ejecutar la consulta. Por lo tanto, si una entidad tiene varias
    * relaciones con otras entidades, la carga EAGER podría ser costosa en términos
    * de rendimiento.*/

    /* CascadeType => es un tipo de enumeración que se utiliza en JPA (Java Persistence API)
     * para especificar cómo se propagarán las operaciones de persistencia (como guardar,
     * actualizar o eliminar) desde una entidad principal a sus entidades relacionadas.
    */

}
