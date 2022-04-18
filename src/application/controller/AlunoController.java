package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Aluno;
import application.model.Curso;
import application.persistence.AlunoDao;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlunoController implements IAlunoController {

	private AlunoDao alDao;
	private TextField tfAlunoId;
	private TextField tfAlunoNome;
	private TextField tfAlunoEmail;
	private TextField tfAlunoTelefone;
	private TextField tfAlunoCodCurso;
	private TextArea taAlunoLista;
	private String cmd;

	public AlunoController(AlunoDao alDao, TextField tfAlunoId, TextField tfAlunoNome, TextField tfAlunoEmail,
			TextField tfAlunoTelefone, TextField tfAlunoCodCurso, TextArea taAlunoLista, String cmd) {
		this.alDao = alDao;
		this.tfAlunoId = tfAlunoId;
		this.tfAlunoNome = tfAlunoNome;
		this.tfAlunoEmail = tfAlunoEmail;
		this.tfAlunoTelefone = tfAlunoTelefone;
		this.tfAlunoCodCurso = tfAlunoCodCurso;
		this.taAlunoLista = taAlunoLista;
		this.cmd = cmd;
	}

	@Override
	public void saveAluno() throws SQLException {
		Aluno al = createAluno();
		alDao.insereAluno(al);
		clean();
	}

	@Override
	public void findAluno() throws SQLException {
		Aluno al = createAluno();
		al = alDao.consultaAluno(al);
		tfAlunoId.setText(String.valueOf(al.getId()));
		tfAlunoNome.setText(al.getNome());
		tfAlunoEmail.setText(al.getEmail());
		tfAlunoTelefone.setText(al.getTelefone());
		tfAlunoCodCurso.setText(String.valueOf(al.getCurso().getCodigo()));
	}

	@Override
	public void findAllAluno() throws SQLException {
		clean();
		List<Aluno> consultaCursos = alDao.consultaAlunos();
		StringBuffer buffer = new StringBuffer();
		for (Aluno al : consultaCursos) {
			buffer.append(al+"\n");
		}
		taAlunoLista.setText(buffer.toString());
	}

	@Override
	public void updateAluno() throws SQLException {
		Aluno al = createAluno();
		alDao.atualizaAluno(al);
		clean();
	}

	@Override
	public void deleteAluno() throws SQLException {
		Aluno al = createAluno();
		alDao.excluiAluno(al);
		clean();
	}

	private Aluno createAluno() {
		Aluno al = new Aluno();
		if (cmd.contains("Inserir") || cmd.contains("Atualizar")) {
			al.setId(Integer.parseInt(tfAlunoId.getText()));
			al.setNome(tfAlunoNome.getText());
			al.setEmail(tfAlunoEmail.getText());
			al.setTelefone(tfAlunoTelefone.getText());

			Curso c = new Curso();
			c.setCodigo(Integer.parseInt(tfAlunoCodCurso.getText()));

			al.setCurso(c);
		}
		if (cmd.contains("Excluir") || cmd.contains("Buscar")) {
			al.setId(Integer.parseInt(tfAlunoId.getText()));
		}

		return al;
	}

	private void clean() {
		tfAlunoId.setText("");
		tfAlunoNome.setText("");
		tfAlunoEmail.setText("");
		tfAlunoTelefone.setText("");
		tfAlunoCodCurso.setText("");
		taAlunoLista.setText("");
	}
}
