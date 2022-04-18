package application.persistence;

import java.sql.SQLException;
import java.util.List;

import application.model.Aluno;

public interface IAlunoDao {

	public void insereAluno   (Aluno al) throws SQLException;
	public void atualizaAluno (Aluno al) throws SQLException;
	public void excluiAluno   (Aluno al) throws SQLException;
	public Aluno consultaAluno (Aluno al) throws SQLException;
	public List<Aluno> consultaAlunos () throws SQLException;
	
}
