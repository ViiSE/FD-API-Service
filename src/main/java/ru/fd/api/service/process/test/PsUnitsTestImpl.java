/*
 * Copyright 2020 ViiSE
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

package ru.fd.api.service.process.test;

import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.UnitImpl;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.entity.UnitsImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsUnitsTestImpl implements Process<Units, Void> {

    @Override
    public Units answer(Void v) {
        Unit unit1 = new UnitImpl("uni_1", "Unit 1");
        Unit unit2 = new UnitImpl("uni_2", "Unit 2");
        return new UnitsImpl(new ArrayList<>() {{ add(unit1); add(unit2); }});

    }
}
