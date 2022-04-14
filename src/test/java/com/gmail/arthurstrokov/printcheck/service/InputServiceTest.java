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

class InputServiceTest {

    InputService inputService = Mockito.mock(InputService.class);

    @BeforeEach
    void setUp() {
        when(inputService.readIncomingDataFromFile(any(Path.class))).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
        when(inputService.readIncomingDataFromSomewhere()).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
    }

    @Test
    void readFromSomewhere() {
        Path of = Path.of("inputValues.txt");
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", inputService.readIncomingDataFromFile(of));
        assertNotEquals("4-3 4-6 2-1 9-2 8-", inputService.readIncomingDataFromFile(of));
        assertNotNull(inputService.readIncomingDataFromFile(of));

        assertThrows(IOException.class, () -> Files.readString(Path.of("input.txt")));
    }

    @Test
    void testReadFromSomewhere() {
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", inputService.readIncomingDataFromSomewhere());
        assertNotEquals("4-3 4-6 2-1 9-2 8-", inputService.readIncomingDataFromSomewhere());
        assertNotNull(inputService.readIncomingDataFromSomewhere());
        assertDoesNotThrow(() -> inputService.readIncomingDataFromSomewhere());
    }
}