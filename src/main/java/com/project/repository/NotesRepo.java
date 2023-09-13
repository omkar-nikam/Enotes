package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Notes;
import com.project.entity.User;

@Repository
public interface NotesRepo extends JpaRepository<Notes, Integer>  {

	
	public List<Notes> findByUser(User user);

	
	
	
}
