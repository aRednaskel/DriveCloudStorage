package com.projects.storage.DriveCloudStorage.services.interfaces;

import com.projects.storage.DriveCloudStorage.model.Note;

import java.util.List;

public interface NoteService {

    int create(Note note);

    void update(Note note);

    Integer getUserId(Integer noteId);

    List<Note> getUserNotes(Integer userId);

    void delete(Integer noteId);
}
