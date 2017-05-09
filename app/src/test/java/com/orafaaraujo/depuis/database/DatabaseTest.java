package com.orafaaraujo.depuis.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.text.TextUtils;

import com.orafaaraujo.depuis.BuildConfig;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Calendar;
import java.util.List;

/**
 * Test of Database.
 *
 * Created by Rafael Araujo on 04/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 25)
public class DatabaseTest {

    private FactDatabase mDatabase;

    private Calendar mCalendar;

    @Before
    public void setup() {

        mCalendar = Calendar.getInstance();

//        mDatabase = new MockDatabase(false);
        mDatabase = new SQLiteDatabase(RuntimeEnvironment.application.getApplicationContext());
    }

    @Test
    public void save() throws Exception {

        long factId = mDatabase.saveFact(provideFact());
        assertTrue(factId > -1);

        final List<Fact> facts = mDatabase.fetchAll();

        assertNotNull(facts);
        assertFalse(facts.isEmpty());
        assertEquals(1, facts.size());
    }

    @Test
    public void update() {

        Fact fact = provideFact();
        long factId = mDatabase.saveFact(fact);

        Fact savedFact = mDatabase.findFact(factId);
        assertNotNull(savedFact);

        Fact changeFact = Fact.builder()
                .setId(savedFact.id())
                .setStartTime(savedFact.startTime() + 1000)
                .setTitle("Updated!")
                .setComment("Updated!")
                .setEndTime(savedFact.endTime() + 1000)
                .build();
        mDatabase.updateFact(changeFact);

        Fact updateFact = mDatabase.findFact(changeFact.id());
        assertNotNull(updateFact);

        assertFalse(updateFact.startTime() == savedFact.startTime());
        assertFalse(TextUtils.equals(updateFact.title(), savedFact.title()));
        assertFalse(TextUtils.equals(updateFact.comment(), savedFact.comment()));
        assertFalse(updateFact.endTime() == savedFact.endTime());

        assertTrue(updateFact.startTime() == changeFact.startTime());
        assertTrue(TextUtils.equals(updateFact.title(), changeFact.title()));
        assertTrue(TextUtils.equals(updateFact.comment(), changeFact.comment()));
        assertTrue(updateFact.endTime() == changeFact.endTime());
    }

    @Test
    public void findOne() throws Exception {

        Fact fact = provideFact();

        long factId = mDatabase.saveFact(fact);

        Fact savedFact = mDatabase.findFact(factId);
        assertNotNull(savedFact);
        assertEquals(fact, savedFact);
    }

    @Test
    public void findAll() throws Exception {

        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());

        final List<Fact> facts = mDatabase.fetchAll();

        assertNotNull(facts);
        assertFalse(facts.isEmpty());
        assertEquals(3, facts.size());
    }

    @Test
    public void delete() throws Exception {

        Fact fact = provideFact();
        long factId = mDatabase.saveFact(fact);

        Fact savedFact = mDatabase.findFact(factId);
        assertNotNull(savedFact);

        mDatabase.deleteFact(savedFact);
        Fact deletedFact = mDatabase.findFact(factId);
        assertNull(deletedFact);
    }

    @Test
    public void deleteAll() {

        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());

        final List<Fact> facts = mDatabase.fetchAll();

        assertNotNull(facts);
        assertFalse(facts.isEmpty());
        assertEquals(3, facts.size());

        mDatabase.deleteTable();

        final List<Fact> deletedFacts = mDatabase.fetchAll();

        assertNotNull(deletedFacts);
        assertTrue(deletedFacts.isEmpty());
        assertEquals(0, deletedFacts.size());
    }

    private Fact provideFact() {
        long startTime = mCalendar.getTimeInMillis();
        long endTime = startTime + 1000;

        return Fact.builder()
                .setId(1)
                .setStartTime(startTime)
                .setTitle("new fact")
                .setComment("A longe time ago...")
                .setEndTime(endTime)
                .build();
    }
}
