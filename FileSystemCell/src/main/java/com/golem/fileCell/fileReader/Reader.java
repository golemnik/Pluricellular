package com.golem.fileCell.fileReader;

import java.io.FileNotFoundException;
import java.util.List;

public interface Reader {
    List<String> filerRead (String fileName) throws FileNotFoundException;

}
