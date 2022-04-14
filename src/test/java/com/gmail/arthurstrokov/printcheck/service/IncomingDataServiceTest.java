package com.gmail.arthurstrokov.printcheck.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IncomingDataServiceTest {

    IncomingDataService incomingDataService = Mockito.mock(IncomingDataService.class);

    @BeforeEach
    void setUp() {
        when(incomingDataService.readIncomingDataFromFile(any(Path.class))).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
        when(incomingDataService.readIncomingDataFromSomewhere()).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
    }

    @Test
    void readFromSomewhere() {
        Path of = Path.of("inputValues.txt");
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", incomingDataService.readIncomingDataFromFile(of));
        assertNotEquals("4-3 4-6 2-1 9-2 8-", incomingDataService.readIncomingDataFromFile(of));
        assertNotNull(incomingDataService.readIncomingDataFromFile(of));

        assertThrows(IOException.class, () -> Files.readString(Path.of("input.txt")));
    }

    @Test
    void testReadFromSomewhere() {
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", incomingDataService.readIncomingDataFromSomewhere());
        assertNotEquals("4-3 4-6 2-1 9-2 8-", incomingDataService.readIncomingDataFromSomewhere());
        assertNotNull(incomingDataService.readIncomingDataFromSomewhere());
        assertDoesNotThrow(() -> incomingDataService.readIncomingDataFromSomewhere());
    }
}