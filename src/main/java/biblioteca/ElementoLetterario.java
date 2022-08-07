package biblioteca;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoLetterario {
	@Id
	@SequenceGenerator(name = "evento4_seq", sequenceName = "evento4_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento4_seq")
	private Long id;
	protected String isbn;
	protected String titolo;
	protected Integer annoPubblicazione;
	protected Integer numeroPagine;

	public ElementoLetterario(String isbn, String titolo, Integer annoPubblicazione, Integer numeroPagine) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}
	

	public ElementoLetterario() {
		super();
	}


	@Override
	public String toString() {
		return "ElementoLetterario [isbn=" + isbn + ", titolo=" + titolo + "]";
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(Integer annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public Integer getNumeroPagine() {
		return numeroPagine;
	}

	public void setNumeroPagine(Integer numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

}
