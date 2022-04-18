package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Curso;
import application.persistence.CursoDao;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CursoController implements ICursoController {
	
	private CursoDao cDao;
	private TextField tfCursoCodigo;
	private TextField tfCursoNome;
	private TextField tfCursoArea;
	private TextField tfCursoHoras;
	private TextArea taCursoLista;
	private String cmd;


	public CursoController(CursoDao cDao, TextField tfCursoCodigo, TextField tfCursoNome, TextField tfCursoArea,
			TextField tfCursoHoras, TextArea taCursoLista, String cmd) {
		this.cDao = cDao;
		this.tfCursoCodigo = tfCursoCodigo;
		this.tfCursoNome = tfCursoNome;
		this.tfCursoArea = tfCursoArea;
		this.tfCursoHoras = tfCursoHoras;
		this.taCursoLista = taCursoLista;
		this.cmd = cmd;
	}

	public void saveCurso() throws SQLException {
		Curso c = createCurso(cmd);
		cDao.insereCurso(c);
		clean();
	}
	
	@Override
	public void updateCurso() throws SQLException {
		Curso c = createCurso(cmd);
		cDao.atualizaCurso(c);
		clean();
	}

	@Override
	public void deleteCurso() throws SQLException {
		Curso c = createCurso(cmd);
		cDao.excluiCurso(c);
		clean();
	}
	
	@Override
	public void findCurso() throws SQLException {
		Curso c = createCurso(cmd);
		c = cDao.consultaCurso(c);
		tfCursoCodigo.setText(String.valueOf(c.getCodigo()));
		tfCursoNome.setText(c.getNome());
		tfCursoArea.setText(c.getArea());
		tfCursoHoras.setText(String.valueOf(c.getHoras()));
	}


	@Override
	public void findAllCurso() throws SQLException {
		clean();
		List<Curso> consultaCursos = cDao.consultaCursos();
		StringBuffer buffer = new StringBuffer();
		for (Curso c : consultaCursos) {
			buffer.append(c+"\n");
		}
		taCursoLista.setText(buffer.toString());
	}
	
	private Curso createCurso(String cmd) {
		Curso c = new Curso();
		if (cmd.contains("Inserir") || cmd.contains("Atualizar")) {
			c.setCodigo(Integer.parseInt(tfCursoCodigo.getText()));
			c.setNome(tfCursoNome.getText());
			c.setArea(tfCursoArea.getText());
			c.setHoras(Integer.parseInt(tfCursoHoras.getText()));
		}
		if (cmd.contains("Excluir") || cmd.contains("Buscar")) {
			c.setCodigo(Integer.parseInt(tfCursoCodigo.getText()));
		}
		return c;
	}
	
	private void clean() {
		tfCursoCodigo.setText("");
		tfCursoNome.setText("");
		tfCursoArea.setText("");
		tfCursoHoras.setText("");
		taCursoLista.setText("");
	}
}