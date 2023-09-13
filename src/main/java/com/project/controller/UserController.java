package com.project.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entity.Notes;
import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NotesService notesService;
	
	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email=p.getName();
		User user=userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}
	
	@GetMapping("/addnote")
	public String addnote() {
		return "add_notes";
	}
	
	@GetMapping("/viewnote")
	public String viewnote(Model m,Principal p) {
		User user=getUser(p, m);
		
		List<Notes> notes=notesService.getNotesByUser(user);
		m.addAttribute("noteslist",notes);
		return "view_notes";
	}
	
	@GetMapping("/editnote/{id}")
	public String editnote(@PathVariable int id,Model m) {
		Notes notes=notesService.getNotesById(id);
		m.addAttribute("n", notes);
		return "edit_notes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p,Model m) {
		notes.setDate(LocalDate.now());
		
		notes.setUser(getUser(p, m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg","Notes save Successfully" );
		}
		else {
			session.setAttribute("msg","Somethig went wrong" );
		}
		
		return "redirect:/user/addnote";
	}
	
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p,Model m) {
		notes.setDate(LocalDate.now());
		
		notes.setUser(getUser(p, m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg","Notes update Successfully" );
		}
		else {
			session.setAttribute("msg","Somethig went wrong" );
		}
		
		return "redirect:/user/viewnote";
	}
	
	@GetMapping("/deletenote/{id}")
	public String deletenote(@PathVariable int id,HttpSession session) {
		boolean f=notesService.deleteNotes(id);
		if(f) {
			session.setAttribute("msg","Notes deleted Successfully" );
		}
		else {
			session.setAttribute("msg","Somethig went wrong" );
		}
		return "redirect:/user/viewnote";
	}
	
	
}
