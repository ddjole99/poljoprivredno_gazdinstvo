package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

/**
 * Predstavlja status procesa setve.
 * <p>
 * Status opisuje trenutnu fazu u kojoj se setva nalazi tokom svog životnog
 * ciklusa, od planiranja do završetka ili otkazivanja.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 */
public enum Status {
	PLANIRANA,
	U_TOKU,
	ZAVRSENA,
	OTKAZANA,
}
