package application.persistence;

import java.sql.SQLException;
import java.util.List;

import application.model.Curso;

public interface ICursoDao {

	public void insereCurso   (Curso c) throws SQLException;
	public void atualizaCurso (Curso c) throws SQLException;
	public void excluiCurso   (Curso c) throws SQLException;
	public Curso consultaCurso (Curso c) throws SQLException;
	public List<Curso> consultaCursos () throws SQLException;
	
}
