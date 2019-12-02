package ru.fd.api.service.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component("FDAPIServiceCurrentDirectory")
public class FDAPIServiceCurrentDirectoryImpl implements FDAPIServiceDirectory {

    @Override
    public String directory() {
        return System.getProperty("user.dir") + File.separator;
    }
}
