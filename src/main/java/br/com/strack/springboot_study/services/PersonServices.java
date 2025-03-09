package br.com.strack.springboot_study.services;

import br.com.strack.springboot_study.dtos.v1.PersonDTO;
import br.com.strack.springboot_study.dtos.v2.PersonDTOV2;
import br.com.strack.springboot_study.exceptions.ResourceNotFoundException;

import static br.com.strack.springboot_study.mapper.ObjectMapper.parseObjectsList;
import static br.com.strack.springboot_study.mapper.ObjectMapper.parseObject;

import br.com.strack.springboot_study.mapper.custom.PersonMapper;
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

    @Autowired
    PersonMapper converter;

    private PersonDTO mockPerson(int i) {

        PersonDTO person = new PersonDTO();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel" + i);
        person.setLastName("Strack" + i);
        person.setAddress("Ponta Grossa");
        person.setGender("Male");

        return person;
    }

    public List<PersonDTO> findAll() {
        logger.info("finding all people!");
        return parseObjectsList(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("finding one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("creating one person!");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("creating one person!");
        var entity = converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("updating one person!");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data for this ID!"));

        repository.delete(entity);
    }
}
