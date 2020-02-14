package com.example.bancodedados.frete;

import com.example.bancodedados.cidade.Cidade;
import com.example.bancodedados.cliente.Cliente;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Frete implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="A descricao deve ser preenchida")
	private String descricao;

	@PositiveOrZero(message="O peso deve ser preenchido")
	private double peso;

	@PositiveOrZero(message="O valor deve ser preenchido")
	private double valor;

	@NotNull(message="O cliente deve ser preenchido")
	@ManyToOne(cascade= { CascadeType.MERGE})
	private Cliente cliente;

	@NotNull(message="A cidade deve ser preenchida")
	@ManyToOne(cascade= { CascadeType.MERGE})
	private Cidade cidade;

	public Frete() {}

	public Frete(double valor, String descricao, double peso, Cliente cliente, Cidade cidade) {
		this.valor = valor + valor*cidade.gettaxa()/100;
		this.descricao = descricao;
		this.peso = peso;
		this.cidade= cidade;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof com.example.bancodedados.frete.Frete)) return false;
		com.example.bancodedados.frete.Frete frete = (com.example.bancodedados.frete.Frete) o;
		return Objects.equals(getId(), frete.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
