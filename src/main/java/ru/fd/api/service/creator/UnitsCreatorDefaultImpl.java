/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.creator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.UnitsRepository;

@Service("unitsCreatorDefault")
@Scope("prototype")
public class UnitsCreatorDefaultImpl implements UnitsCreator {

    private final UnitsRepository unitsRepository;

    public UnitsCreatorDefaultImpl(UnitsRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    @Override
    public Units create() throws CreatorException {
        try {
            return unitsRepository.readUnits();
        } catch (RepositoryException ex) {
            throw new CreatorException(ex.getMessage(), ex.getCause());
        }
    }
}

