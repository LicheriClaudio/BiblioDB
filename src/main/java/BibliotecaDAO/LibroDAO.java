package BibliotecaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biblioteca.Libro;
import jpautilPACK.JpaUtil;

public class LibroDAO {

	private static final Logger logger = LoggerFactory.getLogger(LibroDAO.class);

	public void save(Libro object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.persist(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();

			logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public void delete(Libro object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(em.contains(object) ? object : em.merge(object));

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
}
