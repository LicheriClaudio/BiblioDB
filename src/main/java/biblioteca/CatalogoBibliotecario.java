package biblioteca;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import BibliotecaDAO.LibroDAO;
import BibliotecaDAO.PrestitoDAO;
import BibliotecaDAO.RivistaDAO;
import BibliotecaDAO.UserDAO;
import antlr.DiagnosticCodeGenerator;
import java.io.*;

public class CatalogoBibliotecario {
	private static final Logger logger = LoggerFactory.getLogger(CatalogoBibliotecario.class); // syso

	private static final String FILE_PATH = "Text/catalogobibliotecario.txt"; // salvataggio in file.txt

	private Map<String, ElementoLetterario> archivio;

	public CatalogoBibliotecario() {
		this.archivio = new HashMap<String, ElementoLetterario>();
	}

	public void aggiungi(ElementoLetterario nuovoElemento) {
		archivio.put(nuovoElemento.getIsbn(), nuovoElemento);

		logger.info("Elemento aggiunto in archivio. ISBN: {} - Anno Pubblicazione: {}", nuovoElemento.getIsbn(),
				nuovoElemento.getAnnoPubblicazione());
	}

	public void rimuovi(String isbn) {
		ElementoLetterario elementoRimosso = archivio.remove(isbn);
		if (elementoRimosso != null)
			logger.info("Elemento rimosso dall'archivio. ISBN: {} - Anno Pubblicazione: {}", elementoRimosso.getIsbn(),
					elementoRimosso.getAnnoPubblicazione());

	}

	public ElementoLetterario ricercaPerIsbn(String isbn) {

		return archivio.get(isbn);

	}

	public List<ElementoLetterario> ricercaPerAnnoPubblicazione(Integer annoPubblicazione) {

		return archivio.values().stream().filter(elem -> annoPubblicazione.equals(elem.getAnnoPubblicazione()))
				.collect(Collectors.toList());

	}

	public List<Libro> ricercaPerAutore(String autore) {

		return archivio.values().stream().filter(elem -> elem instanceof Libro).map(elem -> (Libro) elem) // cast
				.filter(elem -> autore.equals(elem.getAutore()))
				// filtra Autore
				.collect(Collectors.toList());

	}
	

	

	public void salvaCatalogo() throws IOException {

		String fileString = "";

		for (ElementoLetterario elemento : archivio.values()) {
			if (fileString.length() != 0) {
				fileString += "#";
			}
			if (elemento instanceof Libro) {
				fileString += Libro.toStringFile((Libro) elemento);
			} else if (elemento instanceof Rivista) {
				fileString += Rivista.toStringFile((Rivista) elemento);
			}
		}

		File file = new File(FILE_PATH);
		FileUtils.writeStringToFile(file, fileString, "UTF-8");
		logger.info("Dati salvati correttamente sul file " + FILE_PATH);

	}

	public void caricaCatalogo() throws IOException {
		this.archivio.clear();

		File file = new File(FILE_PATH);

		String fileString = FileUtils.readFileToString(file, "UTF-8");

		List<String> splitElementiString = Arrays.asList(fileString.split("#"));

		for (String curString : splitElementiString) {
			ElementoLetterario elemento = null;
			if (curString.startsWith(Libro.class.getSimpleName())) {
				elemento = Libro.fromStringFile(curString);
			} else if (curString.startsWith(Rivista.class.getSimpleName())) {
				elemento = Rivista.fromStringFile(curString);
			}
			this.archivio.put(elemento.getIsbn(), elemento);

		}
		logger.info("Dati caricati correttamente dal file " + FILE_PATH);

	}

	public static void main(String[] args) {
		CatalogoBibliotecario catalogo = new CatalogoBibliotecario();
		// LIBRI
		Libro l1 = new Libro("11111", "Educazione Siberiana", 1891, 1278, "pippo", "Romanzo");
		Libro l3 = new Libro("48947", "La Sacra BIBBIA", 20, 1278, "cristo", "Comico");
		Libro l4 = new Libro("12565", "Germonimo", 48,751, "brambilla", "Horror");
		Libro l5 = new Libro("65468", "Ammazzate il soldato rayan", 34, 319, "maria", "Rosa");
		LibroDAO libroDAO = new LibroDAO();
		libroDAO.save(l3);
		libroDAO.save(l1);
		libroDAO.save(l4);
		libroDAO.save(l5);
		
		
		// RIVISTE
		Rivista r1 = new Rivista("1112", "Novella2000", 2021, 178, Periodicita.MENSILE);
		Rivista r3 = new Rivista("4232", "TuttoCode", 2016, 150, Periodicita.SETTIMANALE);
		Rivista r4 = new Rivista("2341", "MachineFast", 1968, 30, Periodicita.SEMESTRALE);
		Rivista r5 = new Rivista("1407", "Focus", 2001, 103, Periodicita.SETTIMANALE);
		RivistaDAO rivistaDAO = new RivistaDAO();
		rivistaDAO.save(r1);
		rivistaDAO.save(r3);
		rivistaDAO.save(r4);
		rivistaDAO.save(r5);
		
		
		// UTENTE
		User utente = new User("DiNazareth", "25/12/0", "Jesus", "1f3sde21");
		User utente2 = new User("bollo", "20/01/1385", "mario", "1sad6de2");
		User utente3 = new User("marco", "21/07/0", "bombolo", "1asdde23");
		UserDAO userDAO = new UserDAO();
		userDAO.save(utente);
		userDAO.save(utente2);
		userDAO.save(utente3);
		
		
		// PRESTITO
		Prestito pres = new Prestito(utente, l3, LocalDate.now(), LocalDate.now(), LocalDate.now());
		Prestito pres2 = new Prestito(utente3, l4, LocalDate.now(), LocalDate.now(), null);
		Prestito pres3 = new Prestito(utente2, r1, LocalDate.now(), LocalDate.now(), null);
		Prestito pres4 = new Prestito(utente, r3, LocalDate.now(), LocalDate.now(), null);
		PrestitoDAO prestitoDAO = new PrestitoDAO();
		pres.setDataRestituzioneEffettiva(LocalDate.now().plusDays(150));
//		pres2.setDataRestituzioneEffettiva(LocalDate.now().plusDays(28));
//		pres3.setDataRestituzioneEffettiva(LocalDate.now().plusDays(48));
//		pres4.setDataRestituzioneEffettiva(LocalDate.now().plusDays(15));
		prestitoDAO.save(pres);
		prestitoDAO.save(pres2);
		prestitoDAO.save(pres4);
		prestitoDAO.save(pres3);
		prestitoDAO.searchByLibId("1f3sde21");
		prestitoDAO.searchByLibId("1sad6de2");
		prestitoDAO.searchByLibId("1asdde23");
		prestitoDAO.searchByLibPrestito();
//		prestitoDAO.save(pres2);
//		prestitoDAO.save(pres3);
//		prestitoDAO.save(pres4);

		
		//userDAO.checkPrestitibyUser("1f3sde21");
		
		

//		Libro l2 = saveLibro();
//		Rivista r2 = saveRivista();
//		User utenteUser = saveUtente();
//		Prestito presPrestito = savePrestito();
		catalogo.aggiungi(r1);
		catalogo.aggiungi(l1);
		
		
		
		

		// SCOMMENTA PER TESTARE

		// libroDAO.delete(l3);
		// userDAO.delete(utente2);
		// rivistaDAO.delete(r3);

//		try {
//			catalogo.salvaCatalogo();
//
//			catalogo.caricaCatalogo();
//
//			List<Libro> ricercaPerAutore = catalogo.ricercaPerAutore("pippo");
//
//			ricercaPerAutore.forEach(elem -> System.out.println("Titolo: " + elem.getTitolo()));
//
//		} catch (IOException e) {
//			logger.error("Errore durante la lettura/scrittura", e);
//		}

	}

}
