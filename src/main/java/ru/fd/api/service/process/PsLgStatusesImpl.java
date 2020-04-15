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

package ru.fd.api.service.process;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.log.LoggerService;

@Component("psLgStatuses")
public class PsLgStatusesImpl implements Process<Sendable, Void> {

    private final LoggerService logger;
    private final Process<Sendable, Void> process;

    public PsLgStatusesImpl(
            LoggerService logger,
            @Qualifier("psStatuses") Process<Sendable, Void> process) {
        this.logger = logger;
        this.process = process;
    }

    @Override
    public Sendable answer(Void nothing) throws ProcessException {
        try {
            Sendable result = process.answer(nothing);
            logger.info(PsLgStatusesImpl.class, "Site request statuses");
            return result;
        } catch (ProcessException ex) {
            logger.error(PsLgStatusesImpl.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            throw ex;
        }
    }
}
