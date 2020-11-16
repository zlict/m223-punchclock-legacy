package ch.zli.m223.punchclock.repository;


import ch.zli.m223.punchclock.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
