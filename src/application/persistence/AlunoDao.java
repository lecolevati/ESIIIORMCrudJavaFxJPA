package application.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import application.model.Aluno;
import application.model.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class AlunoDao implements IAlunoDao {

	private SessionFactory sf;
	
	public AlunoDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insereAluno(Aluno al) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(al);
		transaction.commit();
	}

	@Override
	public void atualizaAluno(Aluno al) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(al);
		transaction.commit();
	}

	@Override
	public void excluiAluno(Aluno al) throws SQLException {
		String sql = "DELETE FROM aluno WHERE id_aluno = ?";
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, al.getId());
		query.executeUpdate();
		transaction.commit();
	}

	@Override
	public Aluno consultaAluno(Aluno al) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		al = entityManager.find(Aluno.class, al.getId());
		return al;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> consultaAlunos() throws SQLException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT al.id_aluno, al.nome_aluno, al.email_aluno, al.tel_aluno, ");
		sql.append("c.cod_curso, c.nome_curso, c.area_curso, c.horas_curso ");
		sql.append("FROM aluno al, curso c ");
		sql.append("WHERE al.cod_curso = c.cod_curso");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listAlunos = query.getResultList();
		for (Object[] linha : listAlunos) {
			Aluno al = new Aluno();
			al.setId((int) linha[0]);
			al.setNome(linha[1].toString());
			al.setEmail(linha[2].toString());
			al.setTelefone(linha[3].toString());
			
			Curso c = new Curso();
			c.setCodigo((int) linha[4]);
			c.setNome(linha[5].toString());
			c.setArea(linha[6].toString());
			c.setHoras((int)linha[7]);
			
			al.setCurso(c);
			
			alunos.add(al);
		}
		return alunos;
	}

}
