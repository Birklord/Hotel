package test.com.myapp;

import com.myapp.hotel.model.Customer;
import com.myapp.hotel.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
    @DataJpaTest
    public class CustomerDataAccessServiceTest {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private CustomerRepository customerRepository;

        // write test cases here
        @Test
        public void whenFindByName_thenReturnCustomer() {
            // given
            Customer alex = new Customer("alex");
            entityManager.persist(alex);
            entityManager.flush();

            // when
            Customer found = customerRepository.findByName(alex.getFirstName());

            // then
            assertThat(found.getFirstName())
                    .isEqualTo(alex.getFirstName());
        }




}

