/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.producer.os;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.os.FDAPIServiceDirectory;

@Service("FDAPIServiceDirectoryProducerDefault")
public class FDAPIServiceDirectoryProducerDefaultImpl implements FDAPIServiceDirectoryProducer {

    private final ApplicationContext ctx;

    public FDAPIServiceDirectoryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public FDAPIServiceDirectory getFdAPIServiceCurrentDirectoryInstance() {
        return ctx.getBean("FDAPIServiceCurrentDirectory", FDAPIServiceDirectory.class);
    }
}
