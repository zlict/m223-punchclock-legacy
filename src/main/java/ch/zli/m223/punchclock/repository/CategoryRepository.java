package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
