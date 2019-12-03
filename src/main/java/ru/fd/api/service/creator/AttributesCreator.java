package ru.fd.api.service.creator;

import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.CreatorException;

public interface AttributesCreator {
    Attributes create() throws CreatorException;
}
