package br.com.strack.springboot_study.services;

import br.com.strack.springboot_study.exceptions.ResourceNotFoundException;
import br.com.strack.springboot_study.model.Person;
import br.com.strack.springboot_study.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    PersonRepository repository;

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel" + i);
        person.setLastName("Strack" + i);
        person.setAddress("Ponta Grossa");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        logger.info("finding all people!");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("finding one person!");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));
    }

    public Person create(Person person) {
        logger.info("creating one person!");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("updating one person!");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));

        repository.delete(entity);
    }
}
