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

package ru.fd.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.StatusesCreatorProducer;
import ru.fd.api.service.producer.entity.StatusProducer;
import ru.fd.api.service.producer.entity.StatusesProducer;
import ru.fd.api.service.producer.repository.StatusesRepositoryProducer;

@Service("statusesServiceDefault")
public class StatusesServiceDefaultImpl implements StatusesService {

    @Autowired private StatusProducer statusProducer;
    @Autowired private StatusesProducer statusesProducer;
    @Autowired private StatusesCreatorProducer statusesCrProducer;
    @Autowired private StatusesRepositoryProducer statusesRepositoryProducer;

    @Override
    public StatusProducer statusProducer() {
        return statusProducer;
    }

    @Override
    public StatusesProducer statusesProducer() {
        return statusesProducer;
    }

    @Override
    public StatusesCreatorProducer statusesCreatorProducer() {
        return statusesCrProducer;
    }

    @Override
    public StatusesRepositoryProducer statusesRepositoryProducer() {
        return statusesRepositoryProducer;
    }
}
