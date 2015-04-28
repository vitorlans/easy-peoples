package com.easy.gpessoal.models;


public class Usuarios {
	
	private Integer Id;
	private String Nome;
	private String Sobrenome;
	private String Apelido;
	private String Email;
	private String Senha;
	private String DtNascimento;
	private String Endereco;
	private String Bairro;
	private String Cidade;
	private String Cep;
	private String Telefone;
	private String Status;
	private String DtCriacao;
	private String Imagem;

	public Usuarios(){};
		
	public Usuarios(String nome, String sobrenome, String apelido,
			String email, String dtNascimento, String endereco, String bairro,
			String cidade, String cep, String telefone) {
		super();
		Nome = nome;
		Sobrenome = sobrenome;
		Apelido = apelido;
		Email = email;
		DtNascimento = dtNascimento;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Cep = cep;
		Telefone = telefone;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}

	public String getApelido() {
		return Apelido;
	}

	public void setApelido(String apelido) {
		Apelido = apelido;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getDtNascimento() {
		return DtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		DtNascimento = dtNascimento;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDtCriacao() {
		return DtCriacao;
	}

	public void setDtCriacao(String dtCriacao) {
		DtCriacao = dtCriacao;
	}

	public String getImagem() {
		return Imagem;
	}

	public void setImagem(String imagem) {
		Imagem = imagem;
	}
}
