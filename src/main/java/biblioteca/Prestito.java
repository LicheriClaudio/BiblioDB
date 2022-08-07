package biblioteca;

import java.time.LocalDate;

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

public class Prestito {
	@Id
	@SequenceGenerator(name = "evento3_seq", sequenceName = "evento3_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento3_seq")
	private Long id;
	@ManyToOne
	User utente;
	@ManyToOne
	ElementoLetterario elementoPrestato;

	LocalDate dataInizioPrestito;
	LocalDate dataRestituzionePrevista;
	LocalDate dataRestituzioneEffettiva;

	public Prestito(User utente, ElementoLetterario elementoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {

		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;

	}

	
	
	public Prestito() {

	}
	

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrevista="
				+ dataRestituzionePrevista + ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public ElementoLetterario getElementoPrestato() {
		
		return elementoPrestato;
	}

	public void setElementoPrestato(ElementoLetterario elementoPrestato) {
		this.elementoPrestato = elementoPrestato;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public LocalDate getDataRestituzionePrevista() {
		return dataRestituzionePrevista;
	}

	public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
		this.dataRestituzionePrevista = dataRestituzionePrevista;
	}

	public LocalDate getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

}
