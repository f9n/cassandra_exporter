package com.criteo.nosql.cassandra.exporter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class JmxScraperTest {

    @Test
    public void test_concat_concatenates_array_contents() {
        String[] arrayA = new String[]{"foo","bar","1"};
        String[] arrayB = new String[]{"baz","2"};

        assertArrayEquals(new String[]{"foo","bar","1", "baz", "2"}, JmxScraper.concat(arrayA, arrayB));
    }

    @Test
    public void test_concat_results_in_generic_array_of_expected_type_parameter() {
        String[] arrayA = new String[]{"foo","bar","1"};
        String[] arrayB = new String[]{"baz","2"};

        assertThat(JmxScraper.concat(arrayA, arrayB), instanceOf(String[].class));
    }

    @Test
    public void test_isExtremelySmall_returns_true_for_extreme_scientific_notation() {
        assertTrue("E-111 should be detected as extremely small", JmxScraper.isExtremelySmall(4.032435795457315E-111));
        assertTrue("E-314 should be detected as extremely small", JmxScraper.isExtremelySmall(2.964393875E-314));
        assertTrue("E-100 should be detected as extremely small", JmxScraper.isExtremelySmall(1.0E-100));
        assertTrue("Very small negative should be detected", JmxScraper.isExtremelySmall(-1.0E-200));
    }

    @Test
    public void test_isExtremelySmall_returns_false_for_normal_values() {
        assertFalse("E-5 should not be extremely small", JmxScraper.isExtremelySmall(1.27537762080294E-5));
        assertFalse("E-15 should not be extremely small", JmxScraper.isExtremelySmall(4.26299581518793E-15));
        assertFalse("Normal values should not be extremely small", JmxScraper.isExtremelySmall(0.001));
        assertFalse("null should not be extremely small", JmxScraper.isExtremelySmall(null));
    }

}
