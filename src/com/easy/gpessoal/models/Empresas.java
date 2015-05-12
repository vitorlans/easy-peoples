package com.easy.gpessoal.models;

public class Empresas {

	Integer Id;
	String Empr_Cnpj;
	String Nome;
	String Status;
	
	public Empresas(){}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEmprCnpj() {
		return Empr_Cnpj;
	}

	public void setEmprCnpj(String empr_Cnpj) {
		Empr_Cnpj = empr_Cnpj;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
