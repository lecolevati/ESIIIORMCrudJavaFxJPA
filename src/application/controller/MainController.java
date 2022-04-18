package application.controller;

import java.sql.SQLException;

import org.hibernate.SessionFactory;

import application.persistence.AlunoDao;
import application.persistence.CursoDao;
import application.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField tfCursoCodigo;
	@FXML
	private Label lblCursoCodigo;
	@FXML
	private Label lblCursoNome;
	@FXML
	private Label lblCursoArea;
	@FXML
	private Label lblCursoHoras;
	@FXML
	private TextField tfCursoNome;
	@FXML
	private TextField tfCursoArea;
	@FXML
	private TextField tfCursoHoras;
	@FXML
	private Button btnCursoInserir;
	@FXML
	private Button btnCursoAtualizar;
	@FXML
	private Button btnCursoExcluir;
	@FXML
	private Button btnCursoListar;
	@FXML
	private Button btnCursoBuscar;
	@FXML
	private TextField tfAlunoId;
	@FXML
	private Label lblAlunoId;
	@FXML
	private Label lblAlunoNome;
	@FXML
	private Label lblAlunoEmail;
	@FXML
	private Label lblAlunoTelefone;
	@FXML
	private TextField tfAlunoNome;
	@FXML
	private TextField tfAlunoEmail;
	@FXML
	private TextField tfAlunoTelefone;
	@FXML
	private Button btnAlunoInserir;
	@FXML
	private Button btnAlunoAtualizar;
	@FXML
	private Button btnAlunoExcluir;
	@FXML
	private Button btnAlunoListar;
	@FXML
	private Button btnAlunoBuscar;
	@FXML
	private Label lblAlunoCodCurso;
	@FXML
	private TextField tfAlunoCodCurso;
	@FXML
	private TextArea taAlunoLista;
	@FXML
	private TextArea taCursoLista;

	// Event Listener on Button[#btnCursoBuscar].onAction
	@FXML
	public void acaoCurso(ActionEvent event) {
		String cmd = event.getSource().toString();

		SessionFactory sf = HibernateUtil.getSessionFactory();
		CursoDao cDao = new CursoDao(sf);
		ICursoController cCont = new CursoController(cDao, tfCursoCodigo, tfCursoNome, tfCursoArea, tfCursoHoras,
				taCursoLista, cmd);

		try {
			if (cmd.contains("Inserir")) {
				cCont.saveCurso();
			}
			if (cmd.contains("Atualizar")) {
				cCont.updateCurso();
			}
			if (cmd.contains("Excluir")) {
				cCont.deleteCurso();
			}
			if (cmd.contains("Buscar")) {
				cCont.findCurso();
			}
			if (cmd.contains("Listar")) {
				cCont.findAllCurso();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnAlunoInserir].onAction
	@FXML
	public void acaoAluno(ActionEvent event) {
		String cmd = event.getSource().toString();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		AlunoDao alDao = new AlunoDao(sf);
		IAlunoController alCont = new AlunoController(alDao, tfAlunoId, tfAlunoNome, tfAlunoEmail, tfAlunoTelefone, 
				tfAlunoCodCurso, taAlunoLista, cmd);

		try {
			if (cmd.contains("Inserir")) {
				alCont.saveAluno();
			}
			if (cmd.contains("Atualizar")) {
				alCont.updateAluno();
			}
			if (cmd.contains("Excluir")) {
				alCont.deleteAluno();
			}
			if (cmd.contains("Buscar")) {
				alCont.findAluno();
			}
			if (cmd.contains("Listar")) {
				alCont.findAllAluno();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
