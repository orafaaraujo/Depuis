package com.orafaaraujo.depuis.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.text.TextUtils;

import com.orafaaraujo.depuis.BuildConfig;
import com.orafaaraujo.depuis.model.FactModel;
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

        final List<FactModel> factModels = mDatabase.fetchAll();

        assertNotNull(factModels);
        assertFalse(factModels.isEmpty());
        assertEquals(1, factModels.size());
    }

    @Test
    public void update() {

        FactModel factModel = provideFact();
        long factId = mDatabase.saveFact(factModel);

        FactModel savedFactModel = mDatabase.findFact(factId);
        assertNotNull(savedFactModel);

        FactModel changeFactModel = FactModel.builder()
                .setId(savedFactModel.id())
                .setStartTime(savedFactModel.startTime() + 1000)
                .setTitle("Updated!")
                .setComment("Updated!")
                .setEndTime(savedFactModel.endTime() + 1000)
                .build();
        mDatabase.updateFact(changeFactModel);

        FactModel updateFactModel = mDatabase.findFact(changeFactModel.id());
        assertNotNull(updateFactModel);

        assertFalse(updateFactModel.startTime() == savedFactModel.startTime());
        assertFalse(TextUtils.equals(updateFactModel.title(), savedFactModel.title()));
        assertFalse(TextUtils.equals(updateFactModel.comment(), savedFactModel.comment()));
        assertFalse(updateFactModel.endTime() == savedFactModel.endTime());

        assertTrue(updateFactModel.startTime() == changeFactModel.startTime());
        assertTrue(TextUtils.equals(updateFactModel.title(), changeFactModel.title()));
        assertTrue(TextUtils.equals(updateFactModel.comment(), changeFactModel.comment()));
        assertTrue(updateFactModel.endTime() == changeFactModel.endTime());
    }

    @Test
    public void findOne() throws Exception {

        FactModel factModel = provideFact();

        long factId = mDatabase.saveFact(factModel);

        FactModel savedFactModel = mDatabase.findFact(factId);
        assertNotNull(savedFactModel);
        assertEquals(factModel, savedFactModel);
    }

    @Test
    public void findAll() throws Exception {

        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());

        final List<FactModel> factModels = mDatabase.fetchAll();

        assertNotNull(factModels);
        assertFalse(factModels.isEmpty());
        assertEquals(3, factModels.size());
    }

    @Test
    public void delete() throws Exception {

        FactModel factModel = provideFact();
        long factId = mDatabase.saveFact(factModel);

        FactModel savedFactModel = mDatabase.findFact(factId);
        assertNotNull(savedFactModel);

        mDatabase.deleteFact(savedFactModel);
        FactModel deletedFactModel = mDatabase.findFact(factId);
        assertNull(deletedFactModel);
    }

    @Test
    public void deleteAll() {

        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());
        mDatabase.saveFact(provideFact());

        final List<FactModel> factModels = mDatabase.fetchAll();

        assertNotNull(factModels);
        assertFalse(factModels.isEmpty());
        assertEquals(3, factModels.size());

        mDatabase.deleteTable();

        final List<FactModel> deletedFactModels = mDatabase.fetchAll();

        assertNotNull(deletedFactModels);
        assertTrue(deletedFactModels.isEmpty());
        assertEquals(0, deletedFactModels.size());
    }

    private FactModel provideFact() {
        long startTime = mCalendar.getTimeInMillis();
        long endTime = startTime + 1000;

        return FactModel.builder()
                .setId(1)
                .setStartTime(startTime)
                .setTitle("new fact")
                .setComment("A longe time ago...")
                .setEndTime(endTime)
                .build();
    }
}
