package com.springcourse.SecurityApp.services;

import com.springcourse.SecurityApp.services.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springcourse.SecurityApp.models.Person;
import com.springcourse.SecurityApp.security.PersonDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

    public List<Person> loadUserByRole(String s) throws UsernameNotFoundException {
        List<Person> person = peopleRepository.findByRole(s);

        return person;
    }

    public List<Person> loadAllUser() throws UsernameNotFoundException {
        List<Person> person = peopleRepository.findAll();

        return person;
    }

    public Person loadUserById(int n) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findById(n);

        if (person.isEmpty())
            return null;

        return person.get();
    }


    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }
}
