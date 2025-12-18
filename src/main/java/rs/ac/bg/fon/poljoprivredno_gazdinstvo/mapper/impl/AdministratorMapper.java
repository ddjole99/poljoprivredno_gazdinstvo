package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

@Component
public class AdministratorMapper{
	
	public AdministratorDto toDto(Administrator e) {
		if(e==null)
			return null;
		
		return new AdministratorDto(
				e.getAdministratorID(),
				e.getUsername(),
				e.getEmail());
	}
	
	public Administrator toEntity(AdministratorCreateRequest req) {
		if(req==null)
			return null;
		
		Administrator a=new Administrator();
		a.setUsername(req.getUsername());
		a.setEmail(req.getEmail());
		
		return a;
		
	}
	
	public void update(AdministratorUpdateRequest req, Administrator admin) {
		if(req==null || admin==null)
			return;
		
		admin.setUsername(req.getUsername());
		admin.setEmail(req.getEmail());
	}
}
