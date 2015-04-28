package com.easy.gpessoal.models;

public class Compromissos {
	
	Integer Id;
	String Titulo;
	String Descricao;
	String DataInicio;
	String DataFim;
	String Status;
	Integer IdUser;
	Integer IdEmpr;
	
	public Compromissos(){}
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public String getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(String dataInicio) {
		DataInicio = dataInicio;
	}
	public String getDataFim() {
		return DataFim;
	}
	public void setDataFim(String dataFim) {
		DataFim = dataFim;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Integer getIdUser() {
		return IdUser;
	}
	public void setIdUser(Integer idUser) {
		IdUser = idUser;
	}
	public Integer getIdEmpr() {
		return IdEmpr;
	}
	public void setIdEmpr(Integer idEmpr) {
		IdEmpr = idEmpr;
	}
	
	

}
