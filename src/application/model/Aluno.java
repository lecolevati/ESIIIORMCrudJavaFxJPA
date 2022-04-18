package application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "aluno")
@Entity
public class Aluno {
	
	@Id
	@Column(name = "id_aluno")
	@NotNull
	private int id;

	@Column(name = "nome_aluno", length = 100)
	@NotNull
	private String nome;

	@Column(name = "email_aluno", length = 60)
	@NotNull
	private String email;

	@Column(name = "tel_aluno", length = 11)
	@NotNull
	private String telefone;
	
	@ManyToOne(targetEntity = Curso.class)
	@JoinColumn(name = "cod_curso")
	@NotNull
	private Curso curso;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", curso=" + curso
				+ "]";
	}
	
}
