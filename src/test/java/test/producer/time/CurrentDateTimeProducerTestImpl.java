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

package test.producer.time;

import ru.fd.api.service.producer.time.CurrentDateTimeProducer;
import ru.fd.api.service.time.CurrentDateTime;
import ru.fd.api.service.time.CurrentDateTimeImpl;

public class CurrentDateTimeProducerTestImpl implements CurrentDateTimeProducer {

    @Override
    public CurrentDateTime getCurrentDateTimeDefaultInstance() {
        return new CurrentDateTimeImpl();
    }
}
