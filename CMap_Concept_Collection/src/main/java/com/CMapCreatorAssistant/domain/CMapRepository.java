package com.CMapCreatorAssistant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CMapRepository extends JpaRepository<Codefile, String>{

	
	List<Codefile> findByfileNameContaining(String text);
}
