package io.github.stewseo.lowlevel.restclient;

import java.util.List;

public interface WarningsHandler {

    boolean warningsShouldFailRequest(List<String> warnings);

    WarningsHandler PERMISSIVE = new WarningsHandler() {
        @Override
        public boolean warningsShouldFailRequest(List<String> warnings) {
            return false;
        }

        @Override
        public String toString() {
            return "permissive";
        }
    };
    WarningsHandler STRICT = new WarningsHandler() {
        @Override
        public boolean warningsShouldFailRequest(List<String> warnings) {
            return false == warnings.isEmpty();
        }

        @Override
        public String toString() {
            return "strict";
        }
    };
}
