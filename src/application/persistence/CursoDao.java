package application.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import application.model.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CursoDao implements ICursoDao {

	private SessionFactory sf;
	
	public CursoDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insereCurso(Curso c) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(c);
		transaction.commit();
	}

	@Override
	public void atualizaCurso(Curso c) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(c);
		transaction.commit();
	}

	@Override
	public void excluiCurso(Curso c) throws SQLException {
		String sql = "DELETE FROM curso WHERE cod_curso = ?";
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, c.getCodigo());
		query.executeUpdate();
		transaction.commit();		
	}

	@Override
	public Curso consultaCurso(Curso c) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		c = entityManager.find(Curso.class, c.getCodigo());
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> consultaCursos() throws SQLException {
		List<Curso> cursos = new ArrayList<Curso>();
		String sql = "SELECT cod_curso, nome_curso, area_curso, horas_curso FROM curso";
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> listCursos = query.getResultList();
		for (Object[] linha : listCursos) {
			Curso c = new Curso();
			c.setCodigo((int) linha[0]);
			c.setNome(linha[1].toString());
			c.setArea(linha[2].toString());
			c.setHoras((int)linha[3]);
			
			cursos.add(c);
		}
		return cursos;
	}

}
