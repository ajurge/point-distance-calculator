package com.bipinet.pointsdistance.service;

import java.io.IOException;


public interface DataReaderService {
    short[] readPoints(String fileName, int numberOfPoints) throws IOException;
}
