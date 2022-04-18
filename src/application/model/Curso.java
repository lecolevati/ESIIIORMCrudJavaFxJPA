package application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@Column(name = "cod_curso")
	@NotNull
	private int codigo;

	@Column(name = "nome_curso", length = 100)
	@NotNull
	private String nome;
	
	@Column(name = "area_curso", length = 50)
	@NotNull
	private String area;
	
	@Column(name = "horas_curso")
	@NotNull
	private int horas;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nome=" + nome + ", area=" + area + ", horas=" + horas + "]";
	}
	
}
