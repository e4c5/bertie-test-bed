package com.raditha.bertie.testbed.lambda;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LambdaService {

    public void testProcessJane_usesCorrectValues() {
        var userRepository = mock(UserRepository.class);
        var service = new Service(userRepository);

        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0)); // Lambda here
        String result = service.processJane();
        assertEquals("Jane", result);

        // Potential duplicate block
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User savedUser = captor.getValue();
        assertEquals("Jane", savedUser.getName(), "MUST use 'Jane', not 'John' or any other value");
        assertEquals("jane@example.com", savedUser.getEmail(), "MUST use jane's email");
        assertTrue(savedUser.isActive());
        assertEquals("user", savedUser.getRole());
    }

    public void testProcessJohn_usesCorrectValues() {
        var userRepository = mock(UserRepository.class);
        var service = new Service(userRepository);

        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0)); // Lambda here
        String result = service.processJohn();
        assertEquals("John", result);

        // Potential duplicate block
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User savedUser = captor.getValue();
        assertEquals("John", savedUser.getName(), "MUST use 'John', not 'Jane' or any other value");
        assertEquals("john@example.com", savedUser.getEmail(), "MUST use john's email");
        assertTrue(savedUser.isActive());
        assertEquals("user", savedUser.getRole());
    }

    // Stub classes
    interface UserRepository {
        Object save(Object o);
    }

    class Service {
        Service(UserRepository r) {
        }

        String processJane() {
            return "Jane";
        }

        String processJohn() {
            return "John";
        }
    }

    class User {
        String getName() {
            return "";
        }

        String getEmail() {
            return "";
        }

        boolean isActive() {
            return true;
        }

        String getRole() {
            return "";
        }
    }
}
