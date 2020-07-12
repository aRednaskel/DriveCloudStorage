package com.projects.storage.DriveCloudStorage.services.implementations;

import com.projects.storage.DriveCloudStorage.mapper.NoteMapper;
import com.projects.storage.DriveCloudStorage.model.Note;
import com.projects.storage.DriveCloudStorage.services.interfaces.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @Override
    public int create(Note note) {
        return noteMapper.insert(note);
    }

    @Override
    public void update(Note note) {
        noteMapper.update(note);
    }

    @Override
    public Integer getUserId(Integer noteId) {
        return noteMapper.getUserId(noteId);
    }

    @Override
    public List<Note> getUserNotes(Integer userId) {
        return noteMapper.getUsetNotes(userId);
    };

    @Override
    public void delete(Integer noteId) {
        noteMapper.delete(noteId);
    }

}
