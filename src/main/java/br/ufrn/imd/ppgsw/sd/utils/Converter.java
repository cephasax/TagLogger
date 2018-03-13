package br.ufrn.imd.ppgsw.sd.utils;

import br.ufrn.imd.ppgsw.sd.domain.TempTag;
import br.ufrn.imd.ppgsw.sd.domain.User;

public class Converter {

	public static User tempTagToUser(TempTag tempTag){
		User u = new User();
		u.setMatricula(tempTag.getTag());
		u.setSala(tempTag.getAntenna());
		
		return u;
	}
	
}
