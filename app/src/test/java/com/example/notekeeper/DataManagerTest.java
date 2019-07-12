package com.example.notekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void createNewNote() throws Exception{
        final MainActivity.DataManager dm= MainActivity.DataManager.getInstance();
        final CourseInfo course=dm.getCourse("android_async");
        final String noteTitle="Test Note Title";
        final String noteText= "Body of my test Note";

        int noteIndex = dm.createNewNote();
        NoteInfo newNote=dm.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote=dm.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(),course);
        assertEquals(compareNote.getTitle(),noteTitle);
        assertEquals(compareNote.getText(),noteText);
    }
}