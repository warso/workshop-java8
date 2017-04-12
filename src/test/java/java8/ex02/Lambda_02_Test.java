package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper<T, P> {
        T map(P p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private <T, P> List<T> map(List<P> personList, PersonToAccountMapper<T, P> mapper) {
        // TODO implémenter la méthode
    	List<T> result = new ArrayList<>();
    	for (P p: personList){
    		T acc = mapper.map(p);
    		result.add(acc);
    	}
    	
        return result;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        
        List<Account> result = map(personList, p-> {
        	Account accou = new Account();
        	accou.setOwner(p);
        	accou.setBalance(100);
        	return accou;
        }
        
        		);

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = map(personList, p->p.getFirstname());

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
