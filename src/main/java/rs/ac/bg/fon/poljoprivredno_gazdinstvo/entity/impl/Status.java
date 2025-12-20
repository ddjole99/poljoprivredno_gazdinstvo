package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

/**
 * Predstavlja status procesa setve.
 * 
 * Status opisuje trenutnu fazu u kojoj se setva nalazi tokom svog zivotnog
 * ciklusa, od planiranja do zavrsetka ili otkazivanja.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 */
public enum Status {
	PLANIRANA,
	U_TOKU,
	ZAVRSENA,
	OTKAZANA,
}
