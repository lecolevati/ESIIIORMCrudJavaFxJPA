package application.controller;

import java.sql.SQLException;

public interface ICursoController {
	
	public void saveCurso  () throws SQLException;
	public void updateCurso() throws SQLException;
	public void deleteCurso() throws SQLException;
	public void findCurso () throws SQLException;
	public void findAllCurso () throws SQLException;
	
}
