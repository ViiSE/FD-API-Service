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

package ru.fd.api.service.scheduler;

import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.notification.NotificationResolver;
import ru.fd.api.service.notification.Notifier;

import java.util.List;

@Component("notificationsScheduler")
//@EnableAsync
public class NotificationsScheduler {

    private final NotificationResolver<Customer> notificationResolver;
    private final Notifier<Customer> notifier;

    public NotificationsScheduler(NotificationResolver<Customer> notificationResolver, Notifier<Customer> notifier) {
        this.notificationResolver = notificationResolver;
        this.notifier = notifier;
    }

//    @Async
//    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
    public void a() {
//        System.out.println("Checked...");

        List<Customer> customers = notificationResolver.consumers();
        if(!customers.isEmpty()) {
            notifier.notify(customers);
        }
    }
}
