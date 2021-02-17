package com.CMapCreatorAssistant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CMapData extends JpaRepository<Codefile, String>{	
	Codefile findByfileName(String text);
}
