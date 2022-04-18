package application.controller;

import java.sql.SQLException;

public interface IAlunoController {
	
	public void saveAluno  () throws SQLException;
	public void updateAluno() throws SQLException;
	public void deleteAluno() throws SQLException;
	public void findAluno () throws SQLException;
	public void findAllAluno () throws SQLException;

}
