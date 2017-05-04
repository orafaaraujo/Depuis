package com.orafaaraujo.depuis.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

        mDatabase.saveFact(provideFact());

        final List<Fact> facts = mDatabase.fetchAll();

        assertNotNull(facts);
        assertFalse(facts.isEmpty());
        assertEquals(1, facts.size());
    }

    @Test
    public void findOne() throws Exception {

        Fact fact = provideFact();

        mDatabase.saveFact(fact);

        Fact savedFact = mDatabase.findFact(fact);

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
        mDatabase.saveFact(fact);

        Fact savedFact = mDatabase.findFact(fact);
        assertNotNull(savedFact);

        mDatabase.deleteFact(savedFact);
        Fact deletedFact = mDatabase.findFact(fact);
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
        return Fact.builder()
                .setId(1)
                .setStartTime(mCalendar.getTimeInMillis())
                .setTitle("new fact")
                .setComment("A longe time ago...")
                .setCount(true)
                .build();
    }
}
