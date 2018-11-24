package br.edu.ifsuldeminas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuarios {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer Id;
		private String nome;
		@Column(unique = true)
		private String cpf;
		private Integer pontuacao;
		private String senha;
		@OneToOne
		private Grupos grupo;
		
		private String telefone;
		private String rua;
		private Integer num;
		private String bairro;
	
		
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
		}
		public String getNome() {
			return nome;
		}
		
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		
		public Grupos getGrupo() {
			return grupo;
		}
		public void setGrupo(Grupos grupo) {
			this.grupo = grupo;
		}

		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getRua() {
			return rua;
		}
		public void setRua(String rua) {
			this.rua = rua;
		}
		public Integer getNum() {
			return num;
		}
		public void setNum(Integer num) {
			this.num = num;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		
		public Integer getPontuacao() {
			return pontuacao;
		}
		public void setPontuacao(Integer pontuacao) {
			this.pontuacao = pontuacao;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
}
