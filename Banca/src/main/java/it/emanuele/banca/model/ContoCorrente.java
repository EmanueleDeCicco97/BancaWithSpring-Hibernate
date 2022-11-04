package it.emanuele.banca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name =  "conto_corrente")
public class ContoCorrente {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column ( name = "numero_conto",unique = true)
	private String numeroConto;
	@Column ( name = "saldo")
	private double saldo;

	@ManyToOne
	@JoinColumn(name="id_correntista",nullable = false)
	private UtenteBancario utentebancario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroConto() {
		return numeroConto;
	}

	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public UtenteBancario getUtentebancario() {
		return utentebancario;
	}

	public void setUtentebancario(UtenteBancario utentebancario) {
		this.utentebancario = utentebancario;
	}
	
	
	
	
}
