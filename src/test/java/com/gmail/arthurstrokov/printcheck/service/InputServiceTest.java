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
        when(inputService.readFromSomewhere(any(Path.class))).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
        when(inputService.readFromSomewhere()).thenReturn("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4");
    }

    @Test
    void readFromSomewhere() {
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", inputService.readFromSomewhere(Path.of("test.txt")));
        assertNotEquals("4-3 4-6 2-1 9-2 8-", inputService.readFromSomewhere(Path.of("test.txt")));
        assertNotNull(inputService.readFromSomewhere(Path.of("test.txt")));

        assertThrows(IOException.class, () -> Files.readString(Path.of("test.txt")));
    }

    @Test
    void testReadFromSomewhere() {
        assertEquals("4-3 4-6 2-1 9-2 8-4 8-5 1-4 2-6 3-12 card-4", inputService.readFromSomewhere());
        assertNotEquals("4-3 4-6 2-1 9-2 8-", inputService.readFromSomewhere());
        assertNotNull(inputService.readFromSomewhere());
        assertDoesNotThrow(() -> inputService.readFromSomewhere());
    }
}