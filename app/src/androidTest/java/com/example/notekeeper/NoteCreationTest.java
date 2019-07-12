package com.example.notekeeper;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

//import androidx.test.runner.AndroidJUnit4;

//import static androidx.test.espresso.Espresso.onData;
//import static androidx.test.espresso.Espresso.pressBack;


//creating a IU TEst
@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    static MainActivity.DataManager sDataManager;
@BeforeClass
    public static void classSetUp ()throws Exception{
        sDataManager= MainActivity.DataManager.getInstance();

    }

    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivityRule =new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote(){
        final CourseInfo course=sDataManager.getCourse("java_lang");
        final String notetTitle="Test Note Title";
        final String noteText="This is the body of the test note";
       // ViewInteraction fabNewNote= onView(withId(R.id.fab));
       // fabNewNote.perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.spinner_courses)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class), equalTo(course))).perform( click());
        onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(containsString(course.getTitle()))));
        onView(withId(R.id.text_note_title)).perform(typeText(notetTitle)).check(matches(withText(containsString(notetTitle))));
        onView(withId(R.id.text_note_text)).perform(typeText(noteText), closeSoftKeyboard());
        onView(withId(R.id.text_note_text)).check(matches(withText(containsString(noteText))));
        pressBack();

        int noteIndex=sDataManager.getNotes().size() -1;
        NoteInfo notes = sDataManager.getNotes().get(noteIndex);
        assertEquals(course, notes.getCourse());
        assertEquals(notetTitle, notes.getTitle());
        assertEquals(noteText, notes.getText());




    }
}