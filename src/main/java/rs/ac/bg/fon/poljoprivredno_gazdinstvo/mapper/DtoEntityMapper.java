package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper;

/**
 * Generički mapper interfejs za konverziju između DTO objekata i entiteta.
 * <p>
 * Ovaj interfejs definiše osnovni ugovor za mapiranje podataka
 * između sloja prenosa podataka (DTO) i domenskog modela (Entity).
 * Konkretne implementacije treba da obezbede mapiranje za određene
 * parove DTO–Entity klasa.
 * </p>
 *
 * @param <T> tip DTO objekta
 * @param <E> tip entiteta
 */
public interface DtoEntityMapper<T, E> {

	/**
     * Konvertuje entitet u odgovarajući DTO objekat.
     *
     * @param e entitet koji se mapira u DTO
     * @return DTO objekat koji odgovara prosleđenom entitetu
     */
	T toDto(E e);

	/**
     * Konvertuje DTO objekat u odgovarajući entitet.
     *
     * @param t DTO objekat koji se mapira u entitet
     * @return entitet koji odgovara prosleđenom DTO objektu
     */
	E toEntity(T t);

}
