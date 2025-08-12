package com.academico.web.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Mensagem<T> {
	
	private Integer status;
	private String mensagem;
    private T dados; 
    private String path;
    private LocalDateTime data;
    private String metodo;
    
    
    private Mensagem(Builder<T> builder) {
        this.status = builder.status;
        this.mensagem = builder.mensagem;
        this.dados = builder.dados;
        this.path = builder.path;
        this.data = builder.data;
        this.metodo = builder.metodo;
    }
    

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public T getDados() {
		return dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonFormat(pattern="dd/MM/yyyy")
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}


	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getMetodo() {
		return metodo;
	}


	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}




	public static class Builder<T> {
        private Integer status;
        private String mensagem;
        private T dados;
        private String path;
        private LocalDateTime data;
        private String metodo;

        public Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T> mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public Builder<T> dados(T dados) {
            this.dados = dados;
            return this;
        }

        public Builder<T> path(String path) {
            this.path = path;
            return this;
        }

        public Builder<T> data(LocalDateTime data) {
            this.data = data;
            return this;
        }
        
        public Builder<T> metodo(String metodo) {
            this.metodo = metodo;
            return this;
        }
        
        public Mensagem<T> build() {
            return new Mensagem<>(this);
        }
    }

}
