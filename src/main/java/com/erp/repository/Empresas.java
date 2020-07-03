package com.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.erp.model.Empresa;

public class Empresas implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager maneger;
	
	public Empresas() {
	}
	
	public Empresas(EntityManager manager) {
		this.maneger = manager;
	}
	
	// Busca no banco atravez do ID
	public Empresa porId(Long id) {
		return maneger.find(Empresa.class, id);
	}
	
	// faz uma pesquisa no banco atravez de parte do nome
	public List<Empresa> pesquisar(String nome){
		TypedQuery<Empresa> query = maneger.createQuery("from Empresa where nomeFantasia like :nomeFatasia", Empresa.class);
		query.setParameter("nomeFantasia", nome + "%");
		return query.getResultList();
	}
	
	// A propriedade merge salva um registro, caso já tenha um ele atualiza
	public Empresa guardar(Empresa empresa) {
		return maneger.merge(empresa);
	}
	
	// remove um registro dentro do contexto do maneger
	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId());
		maneger.remove(empresa);
	}

}
