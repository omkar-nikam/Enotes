package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Notes;
import com.project.entity.User;
import com.project.repository.NotesRepo;

@Service
public class NotesServiceimpl implements NotesService {

	@Autowired
	private NotesRepo notesRepo;
	
	@Override
	public Notes saveNotes(Notes notes) {
		 
		return notesRepo.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		
		return notesRepo.findById(id).get();
	}

	@Override
	public List<Notes> getNotesByUser(User user) {
		
		return notesRepo.findByUser(user);
	}

	@Override
	public Notes updateNotes(Notes notes) {
				return notesRepo.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		Notes notes=notesRepo.findById(id).get();
		if(notes!=null) {
		notesRepo.delete(notes);
		return true;
		}
		
		return false; 
	}

}
