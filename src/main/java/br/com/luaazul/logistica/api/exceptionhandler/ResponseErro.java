package br.com.luaazul.logistica.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseErro {

	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	
	private Map<String, String> campos;
	
	public ResponseErro() {
		// TODO Auto-generated constructor stub
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Map<String, String> getCampos() {
		return campos;
	}

	public void setCampos(Map<String, String> campos) {
		this.campos = campos;
	}
	
	
	
}
