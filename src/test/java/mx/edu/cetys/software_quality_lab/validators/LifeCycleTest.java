package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.*;
import java.util.logging.Logger;

@DisplayName("LifeCycle test and Display name annotations")
public class LifeCycleTest {
    private static final Logger LOG = Logger.getLogger(LifeCycleTest.class.getName());

    @AfterAll
    static void afterAll() {
        LOG.info("afterAll ejecutado");
    }

    @BeforeEach
    void beforeEach() {
        LOG.info("beforeEach ejecutado");
    }

    @Test
    @DisplayName("Test 1")
    void test1() {
        LOG.info("test1 ejecutado");
    }

    @Test
    @DisplayName("Test 2")
    void test2() {
        LOG.info("test2 ejecutado");
    }

    @AfterEach
    void afterEach() {
        LOG.info("afterEach ejecutado");
    }

    @BeforeAll
    static void beforeAll() {
        LOG.info("beforeAll ejecutado");
    }

}
