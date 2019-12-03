package ru.fd.api.service.creator;

import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;

public interface UnitsCreator {
    Units create() throws CreatorException;
}
