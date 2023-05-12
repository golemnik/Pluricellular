package com.golem.core.schemas.basicInterfaces;

import java.util.List;

public interface CellFactory {
    Cell create (List<String> signature);
    String creationCommand ();
    String commandDescription ();
}
