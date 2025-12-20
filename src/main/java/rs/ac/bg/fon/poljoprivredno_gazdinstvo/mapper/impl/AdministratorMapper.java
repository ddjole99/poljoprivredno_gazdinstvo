package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

/**
 * Mapper za konverziju između administrator entiteta i odgovarajućih DTO objekata.
 * 
 * Ova klasa sadrži logiku za:
 * <ul>
 *   <li>mapiranje {@link Administrator} entiteta u {@link AdministratorDto}</li>
 *   <li>mapiranje {@link AdministratorDto} u {@link Administrator} entitet</li>
 *   <li>ažuriranje postojećeg {@link Administrator} entiteta na osnovu
 *       {@link AdministratorUpdateRequest}</li>
 * </ul>
 * 
 *
 * Mapper je registrovan kao Spring bean pomoću anotacije {@link Component}
 * i koristi se u servisnom sloju.
 * 
 */
@Component
public class AdministratorMapper{
	
	/**
     * Mapira {@link Administrator} entitet u {@link AdministratorDto}.
     *
     * @param e administrator entitet koji se mapira
     * @return DTO administratora koji odgovara prosleđenom entitetu;
     *         {@code null} ako je prosleđeni entitet {@code null}
     */
	public AdministratorDto toDto(Administrator e) {
		if(e==null)
			return null;
		
		return new AdministratorDto(
				e.getAdministratorID(),
				e.getUsername(),
				e.getEmail());
	}
	
	/**
     * Kreira {@link Administrator} entitet na osnovu zahteva za kreiranje.
     * 
     * Ova metoda ne postavlja lozinku, jer se njeno hesiranje i postavljanje
     * vrsi u servisnom sloju.
     * 
     *
     * @param req zahtev za kreiranje administratora
     * @return novi administrator entitet;
     *         {@code null} ako je zahtev {@code null}
     */
	public Administrator toEntity(AdministratorCreateRequest req) {
		if(req==null)
			return null;
		
		Administrator a=new Administrator();
		a.setUsername(req.getUsername());
		a.setEmail(req.getEmail());
		
		return a;
		
	}
	
	/**
     * Azurira postojeci {@link Administrator} entitet na osnovu zahteva za izmenu.
     * 
     * Metoda menja samo dozvoljena polja (korisnicko ime i email).
     * 
     *
     * @param req   zahtev za izmenu administratora
     * @param admin administrator entitet koji se azurira
     */
	public void update(AdministratorUpdateRequest req, Administrator admin) {
		if(req==null || admin==null)
			return;
		
		admin.setUsername(req.getUsername());
		admin.setEmail(req.getEmail());
	}
}
