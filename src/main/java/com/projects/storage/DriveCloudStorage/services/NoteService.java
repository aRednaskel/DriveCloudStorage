package com.projects.storage.DriveCloudStorage.services;

import com.projects.storage.DriveCloudStorage.mapper.NoteMapper;
import com.projects.storage.DriveCloudStorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public boolean isNoteAvailable(String noteTitle) {
        return noteMapper.getNote(noteTitle) == null;
    }

    public int createNote(Note note) {
        return noteMapper.insert(note);
    }

    public Note getNote(String noteTitle) {
        return noteMapper.getNote(noteTitle);
    }

    public List<Note> getUsetNotes(Integer userId) {
        return noteMapper.getUsetNotes(userId);
    };


}
