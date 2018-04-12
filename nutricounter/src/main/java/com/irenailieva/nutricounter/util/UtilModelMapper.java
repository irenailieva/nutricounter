package com.irenailieva.nutricounter.util;

import org.modelmapper.ModelMapper;

//Singleton Pattern for Auto-Mapping using the org.modelmapper.ModelMapper
public class UtilModelMapper {

    private static ModelMapper modelMapper;

    private UtilModelMapper() {}

    public static ModelMapper getInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }
}
