package com.easy.gpessoal.models;

public class Drawer {
	
	Integer Imagem;
	String Label;
	
	public Drawer(){}
	
	public Drawer(Integer imagem, String label) {
		super();
		Imagem = imagem;
		Label = label;
	}
	public Integer getImagem() {
		return Imagem;
	}
	public void setImagem(Integer imagem) {
		Imagem = imagem;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	
	
}
