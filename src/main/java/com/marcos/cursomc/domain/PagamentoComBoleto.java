package com.marcos.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.marcos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(EstadoPagamento estado, Pedido pedido, Date dataVencimento,
		Date dataPagamento, Pedido pedido2) {
		super(estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		pedido = pedido2;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
