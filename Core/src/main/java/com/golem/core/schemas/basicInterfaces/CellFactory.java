package com.golem.core.schemas.basicInterfaces;

public interface CellFactory {
    Cell create ();
    String creationCommand ();
    String commandDescription ();
}
