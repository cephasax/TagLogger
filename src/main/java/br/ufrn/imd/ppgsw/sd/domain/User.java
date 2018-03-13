package br.ufrn.imd.ppgsw.sd.domain;

/**
 * @author Cephas
 *
 */
public class User {

	String sala;
	String matricula;
	
	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "User [Sala=" + sala + ", matricula=" + matricula + "]";
	}

	
	
	
	
	
	
	
}
