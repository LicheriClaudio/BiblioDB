package biblioteca;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class User {
	@Id
	@SequenceGenerator(name = "evento2_seq", sequenceName = "evento2_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento2_seq")
	private Long id;
	String Nome;

	String Cognome;
	String Datadinascita;
	@OneToMany
	private Set<User> utente;
	String Numeroditessera;

	public User(String nome, String cognome, String datadinascita, String numeroditessera) {
		super();
		Nome = nome;
		Cognome = cognome;
		Datadinascita = datadinascita;
		Numeroditessera = numeroditessera;
	}

	public User() {

	}

	public Set<User> getUtente() {
		return utente;
	}

	public void setUtente(Set<User> utente) {
		this.utente = utente;
	}
	

	public String getNumeroditessera() {
		return Numeroditessera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getDatadinascita() {
		return Datadinascita;
	}

	public void setDatadinascita(String datadinascita) {
		Datadinascita = datadinascita;
	}

	

	public void setNumeroditessera(String numeroditessera) {
		Numeroditessera = numeroditessera;
	}

}
