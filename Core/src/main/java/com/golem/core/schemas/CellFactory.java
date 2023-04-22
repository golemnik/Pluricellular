package com.golem.core.schemas;

public interface CellFactory {
    Cell create ();
    String creationCommand ();
    String commandDescription ();
}
