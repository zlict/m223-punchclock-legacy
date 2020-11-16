package ch.zli.m223.punchclock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @ManyToOne
    private Role role;

    @JsonIgnore
    @OneToMany
    private Set<Entry> entries;
}
