package eu.corstjens.springboot.hibernate.n1selects.problem;

import eu.corstjens.hibernate.n1selects.model.Brand;
import eu.corstjens.springboot.hibernate.n1selects.problem.util.BrandUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by koencorstjens on 17/04/16.
 */
public class N1Problem {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void before() {
        entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
    }

    @After
    public void afther() {
        entityManagerFactory.close();
    }


    @Test
    public void getBrands() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Brand> brands = entityManager.createQuery("select b from Brand b", Brand.class).getResultList();
        Assert.assertEquals(8, brands.size());
        BrandUtil.printBrandShop(brands);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
