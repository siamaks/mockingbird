package com.smx.mock;

import com.smx.entity.Person;
import com.smx.service.PersonService;
import javax.persistence.EntityManager;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.States;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author SiamaX
 */
@RunWith(JMock.class)
public class JMockTest {
    Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Ignore
    @Test
    public void testInterface() {
        final EntityManager em = context.mock(EntityManager.class);
        final Person person = new Person();
        person.setId(10L);
        
        PersonService service = new PersonService();
        service.setEntityManager(em);

        context.checking(new Expectations() {{
            oneOf(em).find(Person.class, 10L); will(returnValue(person));
        }});

        Person p = service.find(10L);

        Assert.assertEquals(p.getId(), person.getId());
    }

    @Test
    public void testClass() {
        class PersonServiceWrapper {
            PersonService service;

            public void setService(PersonService service) {
                this.service = service;
            }

            public Person find(long id) {
                return service.find(id);
            }
        }

        final States states = context.states("states").startsAs("s1");

        final PersonService personService = context.mock(PersonService.class);
        PersonServiceWrapper wrapper = new PersonServiceWrapper();
        wrapper.setService(personService);

        context.checking(new Expectations() {{
            oneOf(personService).find(10L); 
            will(returnValue(with(any(Person.class))));
            when(states.is("s2"));

            oneOf(personService).find(11L); 
            will(returnValue(with(any(Person.class))));
            when(states.is("s1"));

            oneOf(personService).find(12L);
            will(returnValue(with(any(Person.class))));
            when(states.is("s1"));
            then(states.is("s2"));
        }});

        wrapper.find(11L);
        wrapper.find(12L);
        wrapper.find(10L);
    }
}