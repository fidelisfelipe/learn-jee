package br.com.learn.java.model;

public class PessoaEntity {
	
	public static final String TABLE_NAME = "pessoa";
	
	private int id;
	private String name;
	public PessoaEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
