package com.diogofranca.automatedtestswithjava.modulo5tdd;

import com.diogofranca.automatedtestswithjava.modulo5tdd.model.Person;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService{
	@Override
	public Person createPerson(Person person) {
		var id = new AtomicLong().incrementAndGet();
		person.setId(id);
		if(Objects.isNull(person.getEmail()) || person.getEmail().isBlank())
			throw new IllegalArgumentException("The person e-mail is null or empty!");
		return person;
	}
}
