package com.dnt.concurrentupdate.domain;

import com.dnt.concurrentupdate.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    EntityManager em;

    @Before
    public void loadData() {
        Product product = new Product();
        product.setQuantity(5);
        productRepository.save(product);
    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLock() {
        Product p1 = productRepository.findById(1L).orElse(null);
        Product p2 = productRepository.findById(1L).orElse(null);

        p1.setQuantity(4);
        productRepository.save(p1);
        p2.setQuantity(3);
        productRepository.save(p2);
    }

    @Test
    @Transactional
    public void testOptimisticLockUsingEntityManager() {
        em.persist(new Product());
    }

}
