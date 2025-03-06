package br.com.strack.springboot_study.repository;

import br.com.strack.springboot_study.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
