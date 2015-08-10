package org.gbif.api.model.checklistbank;

import org.gbif.api.model.Constants;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatasetMetricsTest {

    @Test
    public void testEquality() {
        DatasetMetrics metrics = new DatasetMetrics();
        metrics.setDatasetKey(Constants.NUB_DATASET_KEY);

        DatasetMetrics metrics2 = new DatasetMetrics();
        metrics2.setDatasetKey(Constants.NUB_DATASET_KEY);
        assertEquals(metrics, metrics2);
    }
}