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

import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.BalanceImpl;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.BalancesImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsBalancesTestImpl implements Process<Balances, Void> {

    @Override
    public Balances answer(Void v) {
        Balance balance1 = new BalanceImpl("dep_1", 10);
        Balance balance2 = new BalanceImpl("dep_2", 20);
        return new BalancesImpl(new ArrayList<>() {{ add(balance1); add(balance2); }});
    }
}
